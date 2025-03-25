package com.example.polyclinicregister.presentation.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.polyclinicregister.R
import com.example.polyclinicregister.data.remote.dto.Service

@Composable
fun ServiceScreen(
    state: ServiceState,
    onDeleteService: (Int) -> Unit,
    onUpdateService: (Service) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(state.services) { service ->
            ServiceCard(
                service = service,
                onDelete = { id ->
                    onDeleteService(id)
                },
                onUpdate = { updatedService ->
                    onUpdateService(updatedService)
                }
            )
        }
    }
}

@Composable
fun ServiceCard(
    service: Service,
    onDelete: (Int) -> Unit,
    onUpdate: (Service) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(service.name) }
    var price by remember { mutableStateOf(service.price.toString()) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            if (isEditing) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Название") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Цена") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            val updatedPrice = price.toDoubleOrNull()
                            if (updatedPrice != null) {
                                onUpdate(service.copy(name = name, price = updatedPrice))
                                isEditing = false
                            }
                        }
                    ) {
                        Icon(Icons.Default.Done, contentDescription = "Сохранить")
                        Spacer(Modifier.height(4.dp))
                        Text("Сохранить")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { isEditing = false }) {
                        Text("Отмена")
                    }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painter = painterResource(R.drawable.title_24px), contentDescription = null)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Название: ${service.name}",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.credit_card_24px),
                                contentDescription = null
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Цена: ${service.price.toInt()} рублей",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        IconButton(onClick = { isEditing = true }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Редактировать"
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        IconButton(onClick = { onDelete(service.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Удалить"
                            )
                        }
                    }
                }
            }
        }
    }
}