package com.example.polyclinicregister.data.repository

import com.example.polyclinicregister.data.remote.ServiceApi
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.repository.ServiceRepo

class ServiceRepoImpl(private val serviceApi: ServiceApi) : ServiceRepo {
    override suspend fun getServices(): List<Service> {
        return serviceApi.getServices()
    }

//    override suspend fun deleteService(service: Service) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updateService(service: Service) {
//        TODO("Not yet implemented")
//    }
}