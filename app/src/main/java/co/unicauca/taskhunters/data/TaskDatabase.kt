package co.unicauca.taskhunters.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.unicauca.taskhunters.model.Task

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}