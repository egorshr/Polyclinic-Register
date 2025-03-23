package com.example.polyclinicregister.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServiceResponse(
    val id: Int,
    val name: String,
    val price: Double
)
