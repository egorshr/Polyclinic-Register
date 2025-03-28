package com.example.polyclinicregister.presentation.visit

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.polyclinicregister.R
import com.example.polyclinicregister.data.remote.dto.Visit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char
import kotlinx.datetime.format.optional

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(
    state: VisitState,
    onVisitDelete: (Int) -> Unit,
    onVisitCheck: (Boolean, Int) -> Unit,
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDeleteSelected: () -> Unit,
    onFilter: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateRangePickerState = rememberDateRangePickerState()


    Column {
        Row {
            IconButton(
                onClick = {
                    onFilter()
                },
                modifier = modifier
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.filter_alt_24px),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                )
            }

            if (state.checkedVisits.isNotEmpty()) {
                Button(
                    onClick = onDeleteSelected,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Удалить выбранные")
                }
            }
        }
        if (state.isShowDataPicker) {
            DatePickerDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onDateRangeSelected(
                                Pair(
                                    dateRangePickerState.selectedStartDateMillis,
                                    dateRangePickerState.selectedEndDateMillis
                                )
                            )
                            onDismiss()
                        }
                    ) {
                        Text("ОК")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Отмена")
                    }
                },
                modifier = modifier
            ) {
                DateRangePicker(
                    state = dateRangePickerState,
                    title = {
                        Text(
                            text = ""
                        )
                    },
                    showModeToggle = false,
                    modifier = Modifier

                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
        ) {
            items(state.visits) { visit ->
                VisitCard(
                    visit = visit,
                    onDelete = { id ->
                        onVisitDelete(id)
                    },
                    onCheckChange = { checked, id ->
                        onVisitCheck(checked, id)
                    },
                    checkedVisits = state.checkedVisits,

                )
            }
        }
    }

}



@Composable
fun VisitCard(
    visit: Visit,
    checkedVisits: Set<Int>,
    onCheckChange: (Boolean, Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val patientName = "${visit.patient.firstName} ${visit.patient.middleName ?: ""} ${visit.patient.lastName}"
    val doctorName = "${visit.employee.firstName} ${visit.employee.middleName ?: ""} ${visit.employee.lastName}"
    val customFormat = LocalDateTime.Format {
        date(LocalDate.Formats.ISO)
        char(' ')
        hour(); char(':'); minute()
        optional {
            char(':'); second()
            optional {
                char('.'); secondFraction(minLength = 3)
            }
        }
    }
    val isChecked = visit.id in checkedVisits
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    onCheckChange(checked, visit.id)
                },

            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Имя пациента: $patientName",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Принимающий врач: $doctorName",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.percent_24px), contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Скидка: ${visit.discount.percent}%",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Дата и Время: ${visit.dateAndTime}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            IconButton(onClick = { onDelete(visit.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )

            }
        }
    }
}
