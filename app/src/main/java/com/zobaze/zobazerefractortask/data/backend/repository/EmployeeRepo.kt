package com.zobaze.zobazerefractortask.data.backend.repository

import com.zobaze.zobazerefractortask.data.backend.model.EmployeeResponse
import com.zobaze.zobazerefractortask.data.backend.model.NetworkResult

interface EmployeeRepo {
    suspend fun getEmployees(): NetworkResult<EmployeeResponse>
}
