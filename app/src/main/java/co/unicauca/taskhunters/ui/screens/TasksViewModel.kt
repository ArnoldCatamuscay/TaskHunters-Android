package co.unicauca.taskhunters.ui.screens

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import co.unicauca.taskhunters.data.taskList
import co.unicauca.taskhunters.ui.components.Task

class TasksViewModel: ViewModel() {
    private val _tasks = taskList.toMutableStateList()

    val tasks: List<Task>
        get() = _tasks

    fun taskChecked(item: Task) {
        _tasks.remove(item)
    }
}
