package com.yash10019coder.data.backend.repository

import com.yash10019coder.data.backend.api.EmployeeService
import com.yash10019coder.data.backend.model.EmployeeDto
import com.yash10019coder.data.backend.model.EmployeeResponse
import com.yash10019coder.data.backend.model.NetworkResult
import com.yash10019coder.data.backend.model.handleApi
import javax.inject.Inject

class EmployeeRepoImpl @Inject constructor(
    private val employeeService: EmployeeService
) : EmployeeRepo {
    override suspend fun getEmployees(): NetworkResult<EmployeeResponse> {
        return handleApi { employeeService.getEmployees() }
    }
}
