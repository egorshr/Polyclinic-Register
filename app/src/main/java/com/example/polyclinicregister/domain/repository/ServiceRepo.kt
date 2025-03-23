package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Service

interface ServiceRepo {
    suspend fun getServices(): List<Service>
//    suspend fun deleteService(service: Service)
//    suspend fun updateService(service: Service)
}