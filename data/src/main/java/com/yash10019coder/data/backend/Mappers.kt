package com.yash10019coder.data.backend

import com.yash10019coder.data.backend.model.EmployeeResponse
import com.yash10019coder.data.backend.controller.EmployeeDto

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
