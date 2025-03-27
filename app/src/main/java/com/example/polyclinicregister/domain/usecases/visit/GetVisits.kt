package com.example.polyclinicregister.domain.usecases.visit

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Visit
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.VisitRepo
import kotlinx.datetime.Instant

class GetVisits(private val visitRepo: VisitRepo) {
    suspend operator fun invoke(startDate: Instant? = null, endDate: Instant? = null): List<Visit> {
        return visitRepo.getVisits(startDate, endDate)
    }
}