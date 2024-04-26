package co.unicauca.taskhunters.ui

/**
 * Provides Factory to create instance of ViewModel for the entire TaskHunters app
 */
/*
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
 * [TaskHuntersHiltApplication].
 */
fun CreationExtras.taskHuntersApplication(): TaskHuntersHiltApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TaskHuntersHiltApplication)*/