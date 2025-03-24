package com.example.polyclinicregister.domain.usecases.visit

import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Visit
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.VisitRepo

class GetVisits(private val visitRepo: VisitRepo) {
    suspend operator fun invoke(): List<Visit> {
        return visitRepo.getVisits()
    }
}