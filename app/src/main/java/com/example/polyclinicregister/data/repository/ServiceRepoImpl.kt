package com.example.polyclinicregister.data.repository

import com.example.polyclinicregister.data.remote.ServiceApi
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.ServiceRepo
import kotlinx.coroutines.flow.Flow

class ServiceRepoImpl(private val serviceApi: ServiceApi) : ServiceRepo {
    override suspend fun getServices(order: String): List<Service> {
        return serviceApi.getServices(order)
    }

    override suspend fun deleteService(id: Int) {
        serviceApi.deleteService(id)
    }

    override suspend fun updateService(service: Service) {
        serviceApi.updateService(service)
    }
}