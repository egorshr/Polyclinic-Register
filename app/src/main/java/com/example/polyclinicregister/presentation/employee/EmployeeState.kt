package com.example.polyclinicregister.presentation.employee

import com.example.polyclinicregister.data.remote.dto.Employee

data class EmployeeState(
    val employees: List<Employee> = emptyList()
)
