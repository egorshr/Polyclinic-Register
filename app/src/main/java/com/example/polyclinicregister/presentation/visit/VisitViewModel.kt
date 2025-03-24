package com.example.polyclinicregister.presentation.visit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyclinicregister.domain.usecases.visit.VisitUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class VisitViewModel(private val visitUseCases: VisitUseCases) : ViewModel() {
    var state = MutableStateFlow(VisitState())
        private set

    init {
        getVisits()

    }

    private fun getVisits() {
        viewModelScope.launch {
            val visits = visitUseCases.getVisits()
            state.value = state.value.copy(visits = visits)
        }

    }

    fun deleteVisit(id: Int) {
        viewModelScope.launch {
            visitUseCases.deleteVisit(id)
            getVisits()
        }
    }
}