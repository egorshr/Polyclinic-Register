package com.example.polyclinicregister.domain.usecases.visit

import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.data.remote.dto.Visit
import com.example.polyclinicregister.domain.repository.ServiceRepo
import com.example.polyclinicregister.domain.repository.VisitRepo

class UpdateVisit(private val visitRepo: VisitRepo) {
    suspend operator fun invoke(visit: Visit) {
        visitRepo.updateVisit(visit)
    }
}