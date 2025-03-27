package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepo {
    suspend fun getServices(): List<Service>
    suspend fun deleteService(id: Int)
    suspend fun updateService(service: Service)
}