package co.unicauca.taskhunters.ui.screens.tasks


import android.util.Log
import androidx.lifecycle.ViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.data.TasksRepository
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
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
    * Load task info into _taskUiState
    */
    fun loadTaskData(task: Task, isDaily: Boolean){
        val taskType = if (isDaily) TaskType.DAILY else TaskType.TODO
        _taskUiState.update { task.toTaskUiState() }
        setTaskType(taskType)
    }

    /**
     * Inserts a Daily in the Room database
     */
    suspend fun saveDaily(goBack : () -> Unit) {
        if(!validateInputs()) return
        tasksRepository.insertTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    /**
     * Inserts a To Do in the Room database
     */
    suspend fun saveToDo(goBack : () -> Unit) {
        if(!validateInputs()) return
        _taskUiState.update { it.copy(type = TaskType.TODO) }
        tasksRepository.insertTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    /**
     * Update a Task in the Room database
     */
    suspend fun updateTask(goBack : () -> Unit) {
        //Log.d("updateTask", _taskUiState.value.toTask().toString())
        if(!validateInputs()) return
        tasksRepository.updateTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    /**
     * Delete a Task in the Room database
     */
    suspend fun deleteTask(goBack : () -> Unit) {
        tasksRepository.deleteTask(_taskUiState.value.toTask())
        clearAllFields()
        goBack()
    }

    private fun setTaskType(type: TaskType) {
        _taskUiState.update { it.copy(type = type) }
    }

    fun onTaskTitleChange(newValue: String) {
        _taskUiState.update { it.copy(title = newValue) }
    }

    fun onTaskDescChange(newValue: String) {
        _taskUiState.update { it.copy(description = newValue) }
    }

    fun onDueDateChange(newValue: String) {
        Log.d("printDate", newValue)
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

    private fun validateInputs(): Boolean{
        if (_taskUiState.value.title.isBlank()) {
            SnackBarManager.showMessage(R.string.task_title_error)
            return false
        }
        if (_taskUiState.value.type==TaskType.TODO && _taskUiState.value.dueDate.isBlank()) {
            SnackBarManager.showMessage(R.string.task_due_time_error)
            return false
        }
        return true
    }
}
