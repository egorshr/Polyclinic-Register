package com.example.polyclinicregister.presentation.visit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyclinicregister.domain.usecases.visit.VisitUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant


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

    fun deleteVisit(id: Set<Int>) {
        viewModelScope.launch {
            visitUseCases.deleteVisit(id)
            getVisits()
        }
    }

    fun deleteSelectedVisits() {
        viewModelScope.launch {
            visitUseCases.deleteVisit(state.value.checkedVisits)
            state.value =
                state.value.copy(checkedVisits = emptySet(), visits = visitUseCases.getVisits())
        }
    }

    fun onSelectDateRange(dateRange: Pair<Long?, Long?>) {
        val (startMillis, endMillis) = dateRange
        if (startMillis != null && endMillis != null) {
            val startDate = Instant.fromEpochMilliseconds(startMillis)
            val endDate = Instant.fromEpochMilliseconds(endMillis)
            viewModelScope.launch {
                val visits = visitUseCases.getVisits(startDate, endDate)
                state.value = state.value.copy(visits = visits, isShowDataPicker = false)
            }
        }
    }

    fun onFilter() {
        state.value = state.value.copy(isShowDataPicker = true)
    }

    fun onDismiss() {
        state.value = state.value.copy(isShowDataPicker = false)
    }

//    fun onCheckChange(isChecked: Boolean, id: Int) {
//        state.update { currentState ->
//            currentState.copy(
//                checkedVisits = if (isChecked) {
//                    currentState.checkedVisits + id
//                } else {
//                    currentState.checkedVisits - id
//                }
//            )
//        }
//    }

    fun onCheckChange(isChecked: Boolean, id: Int) {
        val checkedVisits = if (isChecked) {
            state.value.checkedVisits + id
        } else {
            state.value.checkedVisits - id
        }
        state.value = state.value.copy(checkedVisits = checkedVisits)
    }

}