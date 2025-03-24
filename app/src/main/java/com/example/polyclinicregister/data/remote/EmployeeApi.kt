package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.Employee
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow

class EmployeeApi(private val client: HttpClient) {
    suspend fun getEmployees(): List<Employee> {
        return client.get("${BASE_URL}employees").body()
    }
    suspend fun deleteEmployee(id: Int) {
        client.delete("${BASE_URL}employees/$id")
    }

    suspend fun updateEmployee(employee: Employee) {
        client.put("${BASE_URL}employees") {
            contentType(ContentType.Application.Json)
            setBody(employee)
        }
    }
}