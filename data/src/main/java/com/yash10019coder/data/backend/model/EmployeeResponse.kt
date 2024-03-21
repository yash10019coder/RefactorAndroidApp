package com.yash10019coder.data.backend.model


import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: String
) {
    data class Data(
        @SerializedName("employee_age")
        var employeeAge: Int,
        @SerializedName("employee_name")
        var employeeName: String,
        @SerializedName("employee_salary")
        var employeeSalary: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("profile_image")
        var profileImage: String
    )
}
