package com.example.polyclinicregister.data.remote.dto

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class VisitResponse(
    val id: Int,
    val discountId: Int,
    val patientId: Int,
    val employeeId: Int,
    val dateAndTime: Instant
)
