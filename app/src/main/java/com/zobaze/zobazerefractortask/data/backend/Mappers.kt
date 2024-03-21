package com.zobaze.zobazerefractortask.data.backend

import com.zobaze.zobazerefractortask.data.backend.model.EmployeeResponse
import com.zobaze.zobazerefractortask.data.backend.controller.EmployeeDto

object Mappers {
    fun EmployeeResponse.mapEmployeeResponseToDto(): List<EmployeeDto> {
        val response: EmployeeResponse = this
        return response.data.map {
            EmployeeDto(
                id = it.id,
                employeeName = it.employeeName,
                profileImage = it.profileImage,
                employeeAge = it.employeeAge
            )
        }
    }
}
