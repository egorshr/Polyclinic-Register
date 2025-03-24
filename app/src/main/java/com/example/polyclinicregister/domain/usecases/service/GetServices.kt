package com.example.polyclinicregister.domain.usecases.service

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.ServiceRepo

class GetServices(private val serviceRepo: ServiceRepo) {
    suspend operator fun invoke(): List<Service> {
        return serviceRepo.getServices()
    }
}