package com.zobaze.zobazerefractortask.data.backend.repository

import com.zobaze.zobazerefractortask.data.backend.api.EmployeeService
import com.zobaze.zobazerefractortask.data.backend.model.EmployeeResponse
import com.zobaze.zobazerefractortask.data.backend.model.NetworkResult
import com.zobaze.zobazerefractortask.data.backend.model.handleApi
import javax.inject.Inject

class EmployeeRepoImpl @Inject constructor(
    private val employeeService: EmployeeService
) : EmployeeRepo {
    override suspend fun getEmployees(): NetworkResult<EmployeeResponse> {
        try {
            val response = employeeService.getEmployees()
            if (response.status == "success") {
                return NetworkResult.Success(response)
            } else {
                return NetworkResult.Error(message = response.message)
            }
        } catch (e: Exception) {
            return NetworkResult.Exception(e)
        }
    }
}
