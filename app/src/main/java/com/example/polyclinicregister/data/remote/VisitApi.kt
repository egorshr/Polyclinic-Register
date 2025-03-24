package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Visit
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class VisitApi(private val client: HttpClient) {
    suspend fun getVisits(): List<Visit> {
        return client.get("${BASE_URL}visits").body()
    }

    suspend fun deleteVisit(id: Int) {
        client.delete("${BASE_URL}visits/$id")
    }

    suspend fun updateVisit(visit: Visit) {
        client.put("${BASE_URL}visits") {
            contentType(ContentType.Application.Json)
            setBody(visit)
        }
    }

}