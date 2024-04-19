package co.unicauca.taskhunters.ui.screens.tasks

import androidx.lifecycle.ViewModel
import co.unicauca.taskhunters.data.TasksRepository
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
     * Inserts a Daily in the Room database
     */
    suspend fun saveDaily(goBack : () -> Unit) {
        tasksRepository.insertTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    /**
     * Inserts a To Do in the Room database
     */
    suspend fun saveToDo(goBack : () -> Unit) {
        _taskUiState.update {
            it.copy(type = TaskType.TODO)
        }
        tasksRepository.insertTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    fun setTaskType(type: TaskType) {
        _taskUiState.update { it.copy(type = type) }
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
        _taskUiState.update { TaskUiState() }
    }
}
