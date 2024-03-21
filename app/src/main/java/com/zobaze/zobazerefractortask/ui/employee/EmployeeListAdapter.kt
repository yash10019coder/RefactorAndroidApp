package com.zobaze.zobazerefractortask.ui.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.databinding.EmployeeListModel
import com.zobaze.zobazerefractortask.databinding.ItemListViewBinding
import timber.log.Timber

class EmployeeListAdapter(
    private val context: Context,
    private val employeeModelList: MutableList<EmployeeListModel>,
    private val onItemClick: (EmployeeListModel) -> Unit
) : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListViewBinding =
            ItemListViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("EmployeeListAdapter: onBindViewHolder itemsSize %s", employeeModelList.size)
        holder.bind(employeeModelList[position])
        holder.onClick(employeeModelList[position])
    }

    override fun getItemCount(): Int {
        return employeeModelList.size
    }

    fun updateList(list: List<EmployeeListModel>) {
        employeeModelList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeSelectModel: EmployeeListModel) {
            binding.modelList = employeeSelectModel
        }

        fun onClick(employeeListModel: EmployeeListModel) {
            itemView.setOnClickListener {
                onItemClick(employeeListModel)
            }
        }
    }
}
