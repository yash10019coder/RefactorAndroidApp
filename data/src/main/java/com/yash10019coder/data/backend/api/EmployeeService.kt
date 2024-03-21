package com.yash10019coder.data.backend.api

import com.yash10019coder.data.backend.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeService {

    @GET("/employees")
    suspend fun getEmployees(): Response<EmployeeResponse>
}
