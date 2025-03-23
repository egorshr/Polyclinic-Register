package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Employee

interface EmployeeRepo {
    suspend fun getEmployees(): List<Employee>
//    suspend fun deleteEmployee(employee: Employee)
//    suspend fun updateEmployee(employee: Employee)
}