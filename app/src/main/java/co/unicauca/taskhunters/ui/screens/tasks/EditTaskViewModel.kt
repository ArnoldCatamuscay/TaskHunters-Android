package co.unicauca.taskhunters.ui.screens.tasks

import androidx.lifecycle.ViewModel
import co.unicauca.taskhunters.data.TasksRepository
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditTasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    /**
     * Holds current task ui state
     */
    private val _taskUiState = MutableStateFlow(TaskUiState())
    var taskUiState: StateFlow<TaskUiState> = _taskUiState.asStateFlow()

    /**
     * Inserts a Task in the Room database
     */
    suspend fun saveTask(goBack : () -> Unit) {
        tasksRepository.insertTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    fun onTaskTitleChange(newValue: String) {
        _taskUiState.update { it.copy(title = newValue) }
    }

    fun onTaskDescChange(newValue: String) {
        _taskUiState.update { it.copy(description = newValue) }
    }

    fun onDueDateChange(newValue: String) {
        _taskUiState.update { it.copy(dueDate = newValue) }
    }

    fun onClearFieldClick(property: String) {
        _taskUiState.value = when (property) {
            "title" -> _taskUiState.value.copy(title = "")
            "description" -> _taskUiState.value.copy(description = "")
            "dueDate" -> _taskUiState.value.copy(dueDate = "")
            else -> _taskUiState.value
        }
    }

    private fun clearAllFields() {
        _taskUiState.update {
            TaskUiState(
                id = 0,
                title = "",
                dueDate = "",
                dueTime = "",
                description = "",
                flag = false,
                completed = false,
                userId = ""
            )
        }
    }
}

/**
 * Represents Ui State for a Task.
 */
data class TaskUiState(
    val id: Int = 0,
    val title: String = "",
    val type : TaskType = TaskType.DAILY,
    val dueDate: String = "",
    val dueTime: String = "",
    val description: String = "",
    val flag: Boolean = false,
    val completed: Boolean = false,
    val userId: String = "",
)

/**
 * Extension function to convert TaskUiState to Task.
 */
fun TaskUiState.toTask(): Task = Task(
    id = id,
    title = title,
    type = type,
    dueDate = dueDate,
    dueTime = dueTime,
    description = description,
    flag = flag,
    completed = completed,
    userId = userId
)

/**
 * Extension function to convert Task to TaskUiState
 */
/*fun Task.toTaskUiState(): TaskUiState = TaskUiState(
    id = id,
    title = title,
    type = type,
    dueDate = dueDate,
    dueTime = dueTime,
    description = description,
    flag = flag,
    completed = completed,
    userId = userId
)*/