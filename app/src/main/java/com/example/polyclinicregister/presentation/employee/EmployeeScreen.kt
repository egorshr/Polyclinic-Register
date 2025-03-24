package com.example.polyclinicregister.presentation.employee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.role
import androidx.compose.ui.unit.dp
import com.example.polyclinicregister.data.remote.dto.Employee

@Composable
fun EmployeeScreen(
    state: EmployeeState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(state.employees) { employee ->
            EmployeeCard(employee = employee)
        }
    }
}

@Composable
fun EmployeeCard(employee: Employee) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${employee.firstName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Role: ", style = MaterialTheme.typography.bodyMedium)
        }
    }
}