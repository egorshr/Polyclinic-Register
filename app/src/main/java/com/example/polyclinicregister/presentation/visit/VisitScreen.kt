package com.example.polyclinicregister.presentation.visit

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerColors
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polyclinicregister.R
import com.example.polyclinicregister.data.remote.dto.Visit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(
    state: VisitState,
    onVisitDelete: (Set<Int>) -> Unit,
    onVisitCheck: (Boolean, Int) -> Unit,
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDeleteSelected: () -> Unit,
    onFilter: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateRangePickerState = rememberDateRangePickerState()


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    onFilter()
                },

                ) {
                Icon(
                    painter = painterResource(R.drawable.filter_alt_24px),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                )
            }
            if (state.checkedVisits.isNotEmpty()) {
                TextButton(
                    onClick = onDeleteSelected,
                ) {
                    Text(
                        text = "Удалить выбранные",
                        style = MaterialTheme.typography.bodyMedium,
                    )
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
                        Text(
                            text = "ОК",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "Отмена",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                },
                modifier = Modifier
            ) {
                DateRangePicker(
                    state = dateRangePickerState,
                    title = { Text(text = "") },
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
    onDelete: (Set<Int>) -> Unit,
    modifier: Modifier = Modifier
) {
    val patientName =
        "${visit.patient.firstName} ${visit.patient.middleName ?: ""} ${visit.patient.lastName}"
    val doctorName =
        "${visit.employee.firstName} ${visit.employee.middleName ?: ""} ${visit.employee.lastName}"

    val customFormat = DateTimeComponents.Format {
        date(LocalDate.Formats.ISO)
        char(' ')
        hour()
        char(':')
        minute()
        char(':')
        second()
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
                modifier = Modifier.scale(1.1f)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Имя пациента: $patientName",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Принимающий врач: $doctorName",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.percent_24px),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Скидка: ${visit.discount.percent}%",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Дата и Время: ${visit.dateAndTime.format(customFormat, offset = UtcOffset(hours = 3))}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            IconButton(onClick = { onDelete(setOf(visit.id)) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
        }
    }
}
