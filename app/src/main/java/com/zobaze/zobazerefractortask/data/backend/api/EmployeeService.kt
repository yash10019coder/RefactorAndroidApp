package com.zobaze.zobazerefractortask.data.backend.api

import com.zobaze.zobazerefractortask.data.backend.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeService {

    @GET("api/v1/employees")
    suspend fun getEmployees(): EmployeeResponse
}
