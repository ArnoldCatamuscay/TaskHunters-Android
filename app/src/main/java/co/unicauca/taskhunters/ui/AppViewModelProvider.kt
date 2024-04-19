package co.unicauca.taskhunters.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import co.unicauca.taskhunters.TaskHuntersApplication
import co.unicauca.taskhunters.ui.screens.home.HomeViewModel
import co.unicauca.taskhunters.ui.screens.tasks.DailiesViewModel
import co.unicauca.taskhunters.ui.screens.tasks.EditTasksViewModel
import co.unicauca.taskhunters.ui.screens.tasks.ToDoSViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire TaskHunters app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(taskHuntersApplication().container.tasksRepository)
        }
        // Initializer for EditTaskViewModel
        initializer {
            EditTasksViewModel(taskHuntersApplication().container.tasksRepository)
        }
        // Initializer for DailiesViewModel
        initializer {
            DailiesViewModel(taskHuntersApplication().container.tasksRepository)
        }
        // Initializer for ToDoSViewModel
        initializer {
            ToDoSViewModel(taskHuntersApplication().container.tasksRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TaskHuntersApplication].
 */
fun CreationExtras.taskHuntersApplication(): TaskHuntersApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TaskHuntersApplication)