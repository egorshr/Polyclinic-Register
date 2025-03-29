package com.example.polyclinicregister.presentation.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.polyclinicregister.R
import com.example.polyclinicregister.data.remote.dto.Service

@Composable
fun ServiceScreen(
    state: ServiceState,
    onDeleteService: (Int) -> Unit,
    onUpdateService: (Service) -> Unit,
    onDescendingPrice: () -> Unit,
    onAscendingPrice: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        var expanded by rememberSaveable { mutableStateOf(false) }
        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.sort_24px),
                contentDescription = "More options",
                modifier = Modifier.size(40.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            shape = RoundedCornerShape(8.dp),
            offset = DpOffset(x = 20.dp, y = 110.dp)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Дешевле",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                onClick = {
                    onAscendingPrice()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Дороже",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                onClick = {
                    onDescendingPrice()
                    expanded = false
                }
            )
        }
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
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
}

@Composable
fun ServiceCard(
    service: Service,
    onDelete: (Int) -> Unit,
    onUpdate: (Service) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf(service.name) }
    var price by rememberSaveable { mutableStateOf(service.price.toString()) }

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
                    label = {
                        Text(
                            text = "Название",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Цена", style = MaterialTheme.typography.labelSmall) },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                        Icon(Icons.Default.Done, contentDescription = null)
                        Spacer(Modifier.height(4.dp))
                        Text(text = "Сохранить", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { isEditing = false }) {
                        Text(text = "Отмена", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.title_24px),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Название: ${service.name}",
                                style = MaterialTheme.typography.headlineSmall,
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.credit_card_24px),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Цена: ${service.price.toInt()} рублей",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        IconButton(onClick = { isEditing = true }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Редактировать",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        IconButton(onClick = { onDelete(service.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}