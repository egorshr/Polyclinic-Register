package com.example.polyclinicregister.domain.usecases.service

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.ServiceRepo
import kotlinx.coroutines.flow.Flow

class GetServices(private val serviceRepo: ServiceRepo) {
    suspend operator fun invoke(order: String = "asc"): List<Service> {
        return serviceRepo.getServices(order)
    }
}