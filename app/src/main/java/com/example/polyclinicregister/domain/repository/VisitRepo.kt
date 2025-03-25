package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Visit

interface VisitRepo {
    suspend fun getVisits(): List<Visit>
    suspend fun deleteVisit(id: Int)
    suspend fun updateVisit(visit: Visit)
}