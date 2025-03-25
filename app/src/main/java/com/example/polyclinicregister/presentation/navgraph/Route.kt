package com.example.polyclinicregister.presentation.navgraph

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object EmployeeScreen : Route()

    @Serializable
    data object VisitScreen : Route()

    @Serializable
    data object ServiceScreen : Route()

    @Serializable
    data class EditScreen(val id: Int) : Route()
}