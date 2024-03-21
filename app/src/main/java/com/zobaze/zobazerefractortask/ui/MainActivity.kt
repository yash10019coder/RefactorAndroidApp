package com.zobaze.zobazerefractortask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.databinding.ActivityMainBinding
import com.zobaze.zobazerefractortask.ui.employee.EmployeeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val employeeFragment: Fragment = EmployeeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, employeeFragment)
            .commit()
    }
}

