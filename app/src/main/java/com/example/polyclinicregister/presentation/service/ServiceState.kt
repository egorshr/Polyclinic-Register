package com.example.polyclinicregister.presentation.service

import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.data.remote.dto.Visit

data class ServiceState(
    val services: List<Service> = emptyList(),

)