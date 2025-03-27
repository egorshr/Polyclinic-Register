package com.example.polyclinicregister.presentation.visit

import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.polyclinicregister.data.remote.dto.Employee
import com.example.polyclinicregister.data.remote.dto.Visit

data class VisitState(
    val visits: List<Visit> = emptyList(),
    var isShowDataPicker: Boolean = false
)