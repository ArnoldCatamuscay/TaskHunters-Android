package co.unicauca.taskhunters.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.ui.common.composable.InputField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditTaskScreen(
    isDaily: Boolean,
    isCreated: Boolean,
    goBack: () -> Unit,
    coroutineScope: CoroutineScope,
    tasksViewModel: EditTasksViewModel
) {
    val taskUiState by tasksViewModel.taskUiState.collectAsState()
    //val coroutineScope = rememberCoroutineScope()
    EditTaskScreenContent(
        isDaily = isDaily,
        isCreated = isCreated,
        uiState = taskUiState,
        onTaskTitleChange = tasksViewModel::onTaskTitleChange,
        onTaskDescChange = tasksViewModel::onTaskDescChange,
        onDueDateChange = tasksViewModel::onDueDateChange,
        onClearFieldClick = tasksViewModel::onClearFieldClick,
        onReturnClick = { goBack() },
        onCreateClick = {
            coroutineScope.launch {
                tasksViewModel.saveTask(goBack)
            }
        },
        onUpdateClick = {},
        onDeleteClick = {}
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreenContent(
    isDaily: Boolean,
    isCreated: Boolean,
    //modifier: Modifier = Modifier,
    uiState: TaskUiState,
    onTaskTitleChange: (String) -> Unit,
    onTaskDescChange: (String) -> Unit,
    onDueDateChange: (String) -> Unit,
    onClearFieldClick: (String) -> Unit,
    onReturnClick: () -> Unit,
    onCreateClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.weight(0.1f),
                onClick = onReturnClick
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Return")
            }
            Text(
                modifier = Modifier.weight(0.2f),
                text = stringResource(R.string.new_task)
            )
            Spacer(modifier = Modifier.weight(0.7f))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        //Task title
        InputField(
            placeholder = R.string.task_title,
            value = uiState.title,
            onNewValue = onTaskTitleChange,
            onClearClick = { onClearFieldClick("title") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        //Task description
        InputField(
            placeholder = R.string.task_description,
            value = uiState.description,
            onNewValue = onTaskDescChange,
            onClearClick = { onClearFieldClick("description") },
        )
        if (!isDaily) {
            //Due date
            Spacer(modifier = Modifier.padding(8.dp))
            /*val dateState = rememberDatePickerState()
            DatePicker(state = dateState)*/
            InputField(
                placeholder = R.string.task_due_date,
                value = uiState.dueTime,
                onNewValue = onDueDateChange,
                onClearClick = { onClearFieldClick("dueDate") },
            )
        }
        if (!isCreated) {
            TextButton(
                onClick = onCreateClick,
                shape = ButtonDefaults.outlinedShape,
                border = BorderStroke(0.dp, Color.Black),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(text = stringResource(R.string.create_task))
                Icon(imageVector = Icons.Filled.Create, contentDescription = "Create task")
            }
        } else {
            Row {
                TextButton(
                    onClick = onDeleteClick,
                    shape = ButtonDefaults.outlinedShape,
                    border = BorderStroke(0.dp, Color.Black),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                ) {
                    Text(text = stringResource(R.string.delete_task))
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.delete_task)
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                TextButton(
                    onClick = onUpdateClick,
                    shape = ButtonDefaults.outlinedShape,
                    border = BorderStroke(0.dp, Color.Black),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = stringResource(R.string.update_task))
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(id = R.string.update_task)
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
    /*val task = Task(
        id = 0,
        title = "To Do Project",
        dueDate = "",
        dueTime = "",
        description = "Meeting with my friends",
        flag = false,
        completed = false,
        userId = ""
    )*/
    //EditTaskScreen(isDaily = false, onSave = {}, onCancel = {}, onReturn = {})
}