package com.yash10019coder.data.backend.controller

import com.yash10019coder.data.backend.model.NetworkResult
import com.yash10019coder.data.backend.Mappers.mapEmployeeResponseToDto
import com.yash10019coder.data.backend.repository.EmployeeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeController @Inject constructor(
    private val employeeRepo: EmployeeRepo,
) {
    suspend fun getEmployees(): NetworkResult<List<EmployeeDto>> {
        return withContext(Dispatchers.IO) {
            when (val result = employeeRepo.getEmployees()) {
                is NetworkResult.Error -> NetworkResult.Error(code = result.code, result.message)
                is NetworkResult.Exception -> NetworkResult.Exception(result.exception)
                is NetworkResult.Success -> NetworkResult.Success(result.data.mapEmployeeResponseToDto())
            }
        }
    }
}
