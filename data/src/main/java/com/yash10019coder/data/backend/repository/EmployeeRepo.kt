package com.yash10019coder.data.backend.repository

import com.yash10019coder.data.backend.model.EmployeeResponse
import com.yash10019coder.data.backend.model.NetworkResult

interface EmployeeRepo {
    suspend fun getEmployees(): NetworkResult<EmployeeResponse>
}
