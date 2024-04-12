package co.unicauca.taskhunters.ui.components

enum class TaskType { DAILY, TODO }
data class Task(
    val title: String,
    val body: String = "",
    val taskType: TaskType
)
