package com.example.polyclinicregister.domain.usecases.employee

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.EmployeeRepo

class UpdateEmployee(private val employeeRepo: EmployeeRepo) {
    suspend operator fun invoke(employee: Employee) {
        employeeRepo.updateEmployee(employee)
    }
}