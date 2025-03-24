package com.example.polyclinicregister.presentation.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyclinicregister.domain.usecases.employee.EmployeeUseCases
import com.example.polyclinicregister.domain.usecases.service.ServiceUseCases
import com.example.polyclinicregister.presentation.employee.EmployeeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceViewModel(private val serviceUseCases: ServiceUseCases) : ViewModel() {
    var state = MutableStateFlow(ServiceState())
        private set

    init {
        getServices()
    }

    private fun getServices() {
        viewModelScope.launch {
            val services = serviceUseCases.getServices()
            state.value = state.value.copy(services = services)
        }

    }
}