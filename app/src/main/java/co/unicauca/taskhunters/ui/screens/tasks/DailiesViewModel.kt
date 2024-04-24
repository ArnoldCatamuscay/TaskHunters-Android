package co.unicauca.taskhunters.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.unicauca.taskhunters.data.TasksRepository
import co.unicauca.taskhunters.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
class DailiesViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    /**
     * Holds dailies ui state. The list of dailies are retrieved from [TasksRepository]
     * and mapped to [TasksListUiState]
     */
    private val _dailiesUiState = tasksRepository.getAllDailiesStream()
        .map { TasksListUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TasksListUiState()
        )
    var dailiesUiState: StateFlow<TasksListUiState> = _dailiesUiState

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    /**
     * Delete a Task in the Room database
     */
    suspend fun taskChecked(task: Task) {
        tasksRepository.updateFlag(task.id, !task.flag)
    }
}

