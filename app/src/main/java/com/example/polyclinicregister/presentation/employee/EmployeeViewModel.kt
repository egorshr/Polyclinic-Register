package com.example.polyclinicregister.presentation.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyclinicregister.domain.usecases.employee.EmployeeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployeeViewModel(private val employeeUseCases: EmployeeUseCases) : ViewModel() {
    var state = MutableStateFlow(EmployeeState())
        private set

    init {
        getEmployees()

    }

    private fun getEmployees() {
        viewModelScope.launch {
            val employees = employeeUseCases.getEmployees()
            state.value = state.value.copy(employees = employees)
        }

    }
}