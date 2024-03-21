package com.zobaze.zobazerefractortask.ui.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash10019coder.data.backend.controller.EmployeeController
import com.yash10019coder.data.backend.model.NetworkResult
import com.zobaze.zobazerefractortask.databinding.EmployeeListModel
import com.zobaze.zobazerefractortask.mappers.UiMappers.mapEmployeeDtoToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeController: EmployeeController
) : ViewModel() {
    private val _employeeList = MutableStateFlow<List<EmployeeListModel>>(emptyList())
    val employeeList = _employeeList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            loadDataFromApi()
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

    private suspend fun loadDataFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = employeeController.getEmployees()
            when (result) {
                is NetworkResult.Success -> {
                    _employeeList.value = result.data.mapEmployeeDtoToUiModel()
                }

                is NetworkResult.Error -> {
                    Timber.e("Error: message is ${result.message} and code is ${result.code}")
                }

                is NetworkResult.Exception -> {
                    Timber.e(result.exception, "Exception: ${result.exception}")
                }
            }
        }
    }
}
