package com.zobaze.zobazerefractortask.mappers

import com.zobaze.zobazerefractortask.data.backend.controller.EmployeeDto
import com.zobaze.zobazerefractortask.databinding.EmployeeListModel

object UiMappers {
    fun List<EmployeeDto>.mapEmployeeDtoToUiModel(): List<EmployeeListModel> {
        val list = this
        return list.map {
            EmployeeListModel(
                number = it.id,
                name = it.employeeName
            )
        }
    }
}
