package com.zobaze.zobazerefractortask.ui.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zobaze.zobazerefractortask.databinding.EmployeeListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(

) : ViewModel() {
    private val _employeeList = MutableStateFlow<List<EmployeeListModel>>(emptyList())
    val employeeList = _employeeList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _employeeList.value = loadDemiEmployeeData()
            _isLoading.value = false
        }
    }

    private suspend fun loadDemiEmployeeData(): List<EmployeeListModel> {
        delay(1000L)
        val employeeList = mutableListOf<EmployeeListModel>()
        for (i in 1..10) {
            employeeList.add(EmployeeListModel(i, "Employee $i"))
        }
        return employeeList
    }
}
