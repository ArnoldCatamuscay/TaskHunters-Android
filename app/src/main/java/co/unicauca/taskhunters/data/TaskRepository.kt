package co.unicauca.taskhunters.data

import co.unicauca.taskhunters.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of Task from a given data source.
 */
interface TasksRepository {
    /**
     * Retrieve all the tasks from the given data source.
     */
    fun getAllTasksStream(): Flow<List<Task>>

    /**
     * Retrieve all the dailies from the given data source.
     */
    fun getAllDailiesStream(): Flow<List<Task>>

    /**
     * Retrieve all the to do's from the given data source.
     */
    fun getAllToDoStream(): Flow<List<Task>>

    /**
     * Retrieve an task from the given data source that matches with the [id].
     */
    fun getTaskStream(id: Int): Flow<Task?>

    /**
     * Insert task in the data source
     */
    suspend fun insertTask(task: Task)

    /**
     * Delete task from the data source
     */
    suspend fun deleteTask(task: Task)

    /**
     * Update task in the data source
     */
    suspend fun updateTask(task: Task)

    /**
     * Update attribute flag in the data source
     */
    suspend fun updateFlag(taskId: Int, newFlag: Boolean)

    /**
     * Retrieve all the tasks not completed from the given data source.
     */
    fun getAllTasksByFlagStream(): Flow<List<Task>>
}