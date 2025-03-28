package com.example.polyclinicregister.presentation.visit

import com.example.polyclinicregister.data.remote.dto.Visit

data class VisitState(
    val visits: List<Visit> = emptyList(),
    var isShowDataPicker: Boolean = false,
    val checkedVisits: Set<Int> = emptySet()
)