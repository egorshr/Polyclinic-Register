package com.example.polyclinicregister.domain.repository

import com.example.polyclinicregister.data.remote.dto.Visit
import kotlinx.datetime.Instant

interface VisitRepo {
    suspend fun getVisits(startDate: Instant? = null, endDate: Instant? = null): List<Visit>
    suspend fun deleteVisit(id: Set<Int>)
    suspend fun updateVisit(visit: Visit)
}