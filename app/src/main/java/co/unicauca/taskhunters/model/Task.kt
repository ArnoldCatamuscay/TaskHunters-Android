package co.unicauca.taskhunters.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val type: TaskType,
    val dueDate: String = "",
    val dueTime: String = "",
    val description: String = "",
    val flag: Boolean = false,
    val completed: Boolean = false,
    val userId: String = ""
)