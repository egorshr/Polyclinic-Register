package com.example.polyclinicregister.data.repository

import com.example.polyclinicregister.data.remote.EmployeeApi
import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class EmployeeRepoImpl(private val employeeApi: EmployeeApi) : EmployeeRepo {
    override suspend fun getEmployees(): List<Employee> {
        return employeeApi.getEmployees()
    }



    override suspend fun deleteEmployee(id: Int) {
        employeeApi.deleteEmployee(id)
    }

    override suspend fun updateEmployee(employee: Employee) {
        employeeApi.updateEmployee(employee)
    }
}