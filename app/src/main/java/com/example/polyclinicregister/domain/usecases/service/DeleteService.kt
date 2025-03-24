package com.example.polyclinicregister.domain.usecases.service

import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.ServiceRepo

class DeleteService(private val serviceRepo: ServiceRepo) {

    suspend operator fun invoke(id: Int) {
        serviceRepo.deleteService(id)
    }

}