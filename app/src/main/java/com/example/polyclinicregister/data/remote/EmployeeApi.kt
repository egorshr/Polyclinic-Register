package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.EmployeeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EmployeeApi(private val client: HttpClient) {
    suspend fun getEmployees(): List<EmployeeResponse> {
        return client.get("${BASE_URL}employees").body()
    }

}