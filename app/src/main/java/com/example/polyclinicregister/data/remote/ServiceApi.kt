package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Service
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ServiceApi(private val client: HttpClient) {
    suspend fun getServices(): List<Service> {
        return client.get("${BASE_URL}services").body()
    }

    suspend fun deleteService(id: Int) {
        client.delete("${BASE_URL}services/$id")
    }

    suspend fun updateService(service: Service) {
        client.put("${BASE_URL}services") {
            contentType(ContentType.Application.Json)
            setBody(service)
        }
    }

}