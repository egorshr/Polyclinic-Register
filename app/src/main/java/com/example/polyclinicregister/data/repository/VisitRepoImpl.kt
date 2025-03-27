package com.example.polyclinicregister.data.repository

import com.example.polyclinicregister.data.remote.VisitApi
import com.example.polyclinicregister.data.remote.dto.Visit
import com.example.polyclinicregister.domain.repository.VisitRepo
import kotlinx.datetime.Instant

class VisitRepoImpl(private val visitApi: VisitApi) : VisitRepo {
    override suspend fun getVisits(startDate: Instant?, endDate: Instant?): List<Visit> {
        return visitApi.getVisits(startDate, endDate)
    }


    override suspend fun deleteVisit(id: Int) {
        visitApi.deleteVisit(id)
    }

    override suspend fun updateVisit(visit: Visit) {
        visitApi.updateVisit(visit)
    }
}