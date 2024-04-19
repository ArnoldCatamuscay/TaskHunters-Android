package co.unicauca.taskhunters.ui.screens.tasks

import co.unicauca.taskhunters.model.Task

/**
 * Ui State for DailiesScreen and ToDoScreen
 */
data class TasksListUiState(val taskList: List<Task> = listOf())
