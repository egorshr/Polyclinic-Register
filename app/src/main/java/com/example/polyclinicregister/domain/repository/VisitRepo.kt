package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Visit

interface VisitRepo {
    suspend fun getVisits(): List<Visit>
//    suspend fun deleteVisit(visit: Visit)
//    suspend fun updateVisit(visit: Visit)
}