package co.unicauca.taskhunters.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.unicauca.taskhunters.data.TasksRepository
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    /**
     * Holds dailies ui state. The list of tasks are retrieved from [TasksRepository]
     * and mapped to [HomeUiState]
     */
    private val _homeUiState = tasksRepository.getAllTasksByFlagStream()
        .map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    var homeUiState: StateFlow<HomeUiState> = _homeUiState

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    /**
     * Delete a Task in the Room database
     */
    suspend fun taskChecked(task: Task) {
        if (task.type == TaskType.DAILY)
            tasksRepository.updateFlag(task.id, !task.flag)
        else
            tasksRepository.deleteTask(task)
    }
}