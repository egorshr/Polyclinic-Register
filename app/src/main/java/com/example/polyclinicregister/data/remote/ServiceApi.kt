package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.EmployeeResponse
import com.example.polyclinicregister.data.remote.dto.ServiceResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ServiceApi(private val client: HttpClient) {
    suspend fun getServices(): List<ServiceResponse> {
        return client.get("${BASE_URL}services").body()
    }

}