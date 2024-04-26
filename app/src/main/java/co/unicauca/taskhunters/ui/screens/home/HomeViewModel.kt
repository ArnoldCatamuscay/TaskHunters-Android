package co.unicauca.taskhunters.ui.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import co.unicauca.taskhunters.data.TasksRepository
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import co.unicauca.taskhunters.model.service.AccountService
import co.unicauca.taskhunters.ui.components.Screens
import co.unicauca.taskhunters.ui.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val tasksRepository: TasksRepository
) : AppViewModel() {
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

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(Screens.SplashScreen.name)
                else Log.d("Initial-Check","The user isn´t log out")
            }
        }
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