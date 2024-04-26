package co.unicauca.taskhunters.ui.screens.tasks

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import co.unicauca.taskhunters.ui.common.composable.DateField
import co.unicauca.taskhunters.ui.common.composable.InputField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditTaskScreen(
    task: Task = Task(type = TaskType.DAILY),
    isDaily: Boolean,
    isCreated: Boolean,
    goBack: () -> Unit,
    coroutineScope: CoroutineScope,
    editTasksViewModel: EditTasksViewModel = hiltViewModel()
) {
    val taskUiState by editTasksViewModel.taskUiState.collectAsState()
    LaunchedEffect(key1 = task) {
        editTasksViewModel.loadTaskData(task, isDaily)
    }
    //val taskType = if (isDaily) TaskType.DAILY else TaskType.TODO
    //editTasksViewModel.setTaskType(taskType)
    EditTaskScreenContent(
        isCreated = isCreated,
        uiState = taskUiState,
        onTaskTitleChange = editTasksViewModel::onTaskTitleChange,
        onTaskDescChange = editTasksViewModel::onTaskDescChange,
        onDueDateChange = editTasksViewModel::onDueDateChange,
        onClearFieldClick = editTasksViewModel::onClearFieldClick,
        onReturnClick = { goBack() },
        onCreateClick = {
            coroutineScope.launch {
                if (isDaily) {
                    editTasksViewModel.saveDaily(goBack)
                } else {
                    editTasksViewModel.saveToDo(goBack)
                }
            }
        },
        onUpdateClick = {
            coroutineScope.launch {
                editTasksViewModel.updateTask(goBack)
            }
        },
        onDeleteClick = {
            coroutineScope.launch {
                editTasksViewModel.deleteTask(goBack)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreenContent(
    isCreated: Boolean,
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
    val header = if(isCreated){
        if(uiState.type == TaskType.DAILY) R.string.edit_daily else R.string.edit_to_do
    } else {
        if(uiState.type == TaskType.DAILY) R.string.new_daily else R.string.new_to_do
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.weight(0.1f),
                onClick = onReturnClick
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Return")
            }
            Text(
                modifier = Modifier.weight(0.3f),
                text = stringResource(header)
            )
            Spacer(modifier = Modifier.weight(0.6f))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        //Task title
        InputField(
            placeholder = R.string.task_title,
            value = uiState.title,
            onNewValue = onTaskTitleChange,
            onClearClick = { onClearFieldClick("title") },
        )
        //Task description
        InputField(
            placeholder = R.string.task_description,
            value = uiState.description,
            onNewValue = onTaskDescChange,
            onClearClick = { onClearFieldClick("description") },
        )
        //Date picker
        if (uiState.type == TaskType.TODO) {
            val dateState = rememberDatePickerState()
            DateField(
                placeholder = R.string.task_due_date,
                value = uiState.dueDate,
                onNewValue = onDueDateChange,
                state = dateState
            )
        }
        //Buttons
        if (!isCreated) {
            //Create task
            TextButton(
                onClick = onCreateClick,
                shape = ButtonDefaults.outlinedShape,
                border = BorderStroke(0.dp, Color.Black),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
            ) {
                Text(text = stringResource(R.string.create_task))
                Icon(imageVector = Icons.Filled.Create, contentDescription = "Create task")
            }
        } else {
            Row {
                //Delete task
                TextButton(
                    onClick = onDeleteClick,
                    shape = ButtonDefaults.outlinedShape,
                    border = BorderStroke(0.dp, Color.Black),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Text(text = stringResource(R.string.delete_task))
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.delete_task)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                //Update task
                TextButton(
                    onClick = onUpdateClick,
                    shape = ButtonDefaults.outlinedShape,
                    border = BorderStroke(0.dp, Color.Black),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
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
fun PreviewEditTaskScreenContent() {
    val taskUiState = TaskUiState(
        id = 0,
        title = "",
        type = TaskType.TODO,
        dueDate = "",
        dueTime = "",
        description = "",
        flag = false,
        completed = false,
        userId = ""
    )
    EditTaskScreenContent(
        isCreated = true,
        uiState = taskUiState,
        onTaskTitleChange = {},
        onTaskDescChange = {},
        onDueDateChange = {},
        onClearFieldClick = {},
        onReturnClick = {},
        onCreateClick = {},
        onUpdateClick = {},
        onDeleteClick = {}
    )
}