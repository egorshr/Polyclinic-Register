package com.example.polyclinicregister.data.remote

import com.example.polyclinicregister.Constants.BASE_URL
import com.example.polyclinicregister.data.remote.dto.Visit
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class VisitApi(private val client: HttpClient) {
    suspend fun getVisits(): List<Visit> {
        return client.get("${BASE_URL}visits").body()
    }

}