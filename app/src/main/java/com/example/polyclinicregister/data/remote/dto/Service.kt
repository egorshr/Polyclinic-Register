package com.example.polyclinicregister.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: Int,
    val name: String,
    val price: Double
)
