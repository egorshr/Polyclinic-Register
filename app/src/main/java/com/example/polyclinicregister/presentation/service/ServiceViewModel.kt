package com.example.polyclinicregister.presentation.service


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polyclinicregister.data.remote.dto.Service
import com.example.polyclinicregister.domain.usecases.service.ServiceUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    fun deleteService(id: Int) {
        viewModelScope.launch {
            serviceUseCases.deleteService(id)
            getServices()
        }
    }


    fun updateService(updatedService: Service) {
        viewModelScope.launch {
            serviceUseCases.updateService(updatedService)
            state.value = state.value.copy(
                services = state.value.services.map { existingService ->
                    if (existingService.id == updatedService.id) {
                        updatedService
                    } else {
                        existingService
                    }
                }
            )
        }
    }


}