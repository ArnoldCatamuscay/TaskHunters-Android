package co.unicauca.taskhunters.data

import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.ui.components.Task
import co.unicauca.taskhunters.ui.components.TaskType

data class Reward(val imageId: Int, val cost: Int)

val taskList = listOf(
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
)

val recentRewardsList = listOf(
    Reward(imageId = R.drawable.silver_sword_image, cost = 250),
    Reward(imageId = R.drawable.boots_image, cost = 175),
    Reward(imageId = R.drawable.helmet_image, cost = 200),
    Reward(imageId = R.drawable.wooden_shield_image, cost = 120),
    Reward(imageId = R.drawable.silver_sword_image, cost = 250),
    Reward(imageId = R.drawable.boots_image, cost = 175),
    Reward(imageId = R.drawable.helmet_image, cost = 200),
    Reward(imageId = R.drawable.wooden_shield_image, cost = 120),
    Reward(imageId = R.drawable.silver_sword_image, cost = 250),
    Reward(imageId = R.drawable.boots_image, cost = 175),
    Reward(imageId = R.drawable.helmet_image, cost = 200),
    Reward(imageId = R.drawable.wooden_shield_image, cost = 120),
)

val DailiesList = listOf(
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "Do Homework", taskType = TaskType.DAILY),
)

val ToDoList = listOf(
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
)