package co.unicauca.taskhunters.data

import co.unicauca.taskhunters.model.Task
import kotlinx.coroutines.flow.Flow

class OfflineTasksRepository(private val taskDao: TaskDao) : TasksRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()

    override fun getAllDailiesStream(): Flow<List<Task>> = taskDao.getAllDailies()

    override fun getAllToDoStream(): Flow<List<Task>> = taskDao.getAllToDoS()

    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)

    override suspend fun insertTask(task: Task) = taskDao.insert(task)

    override suspend fun deleteTask(task: Task) = taskDao.delete(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)
}