package com.zobaze.zobazerefractortask.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.databinding.FragmentEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class EmployeeFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeBinding
    private lateinit var employeeListAdapter: EmployeeListAdapter
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee, container, false)
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        binding.employeeViewModel = viewModel
        binding.lifecycleOwner = this

        employeeListAdapter =
            EmployeeListAdapter(requireContext(), mutableListOf()) {
                Timber.d("Employee selected: $it")
            }
        binding.employeeListAdapter = employeeListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.employeeList.collect {
                employeeListAdapter.updateList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect {
                if (it != null) {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun newInstance() = EmployeeFragment()
    }
}
