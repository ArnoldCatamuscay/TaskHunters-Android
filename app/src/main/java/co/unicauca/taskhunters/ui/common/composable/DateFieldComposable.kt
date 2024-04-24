package co.unicauca.taskhunters.ui.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    @StringRes placeholder: Int,
    value: String,
    onNewValue: (String) -> Unit,
    state: DatePickerState,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var dateString by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        ReadonlyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            value = value,
            onValueChange = { dateString = it },
            onClick = { showDialog = true },
            label = {
                Text(text = stringResource(id = placeholder))
            }
        )
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            dateString = millisecondsToDate(state.selectedDateMillis)
                            showDialog = false
                            onNewValue(dateString)
                        }
                    ) {
                        Text(text = stringResource(R.string.confirm_date))
                    }
                }
            ) {
                DatePicker(
                    state = state,
                )
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

fun millisecondsToDate(dateMilli: Long?): String {
    var date = ""
    dateMilli?.let {
        date = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate().toString()
    }
    return date
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewDateField() {
    val dateState = rememberDatePickerState()
    DateField(R.string.task_due_date, value = "", onNewValue = {}, state = dateState)
}