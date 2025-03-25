package com.example.polyclinicregister.domain.usecases.visit

import com.example.polyclinicregister.domain.usecases.employee.GetEmployees

data class VisitUseCases(
    val getVisits: GetVisits,
    val deleteVisit: DeleteVisit,
    val updateVisit: UpdateVisit
)
