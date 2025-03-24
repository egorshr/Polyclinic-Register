package com.example.polyclinicregister.domain.usecases.employee

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.domain.repository.EmployeeRepo

class DeleteEmployee(private val employeeRepo: EmployeeRepo) {

    suspend operator fun invoke(id: Int) {
        employeeRepo.deleteEmployee(id)
    }

}