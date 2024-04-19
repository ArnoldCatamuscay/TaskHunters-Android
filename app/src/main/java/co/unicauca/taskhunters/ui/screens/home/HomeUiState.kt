package co.unicauca.taskhunters.ui.screens.home

import co.unicauca.taskhunters.model.Task

data class HomeUiState(
    val taskList: List<Task> = listOf(),
    //val rewardsList: List<> = listOf()
)
