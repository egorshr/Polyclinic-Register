package com.example.polyclinicregister.domain.usecases.employee

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class GetEmployees(private val employeeRepo: EmployeeRepo) {
    suspend operator fun invoke(): List<Employee> {
        return employeeRepo.getEmployees()
    }
}