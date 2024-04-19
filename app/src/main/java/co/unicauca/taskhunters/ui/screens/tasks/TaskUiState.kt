package co.unicauca.taskhunters.ui.screens.tasks

import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType

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
