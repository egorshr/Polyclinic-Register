package com.example.polyclinicregister.domain.usecases.service

import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.ServiceRepo

class UpdateService(private val serviceRepo: ServiceRepo) {
    suspend operator fun invoke(service: Service) {
        serviceRepo.updateService(service)
    }
}