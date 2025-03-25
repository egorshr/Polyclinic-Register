package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepo {
     suspend fun getEmployees(): List<Employee>
     suspend fun deleteEmployee(id: Int)
     suspend fun updateEmployee(employee: Employee)
}