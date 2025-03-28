package com.example.polyclinicregister.domain.usecases.visit

import com.example.polyclinicregister.domain.repository.ServiceRepo
import com.example.polyclinicregister.domain.repository.VisitRepo

class DeleteVisit(private val visitRepo: VisitRepo) {

    suspend operator fun invoke(id: Set<Int>) {
        visitRepo.deleteVisit(id)
    }

}