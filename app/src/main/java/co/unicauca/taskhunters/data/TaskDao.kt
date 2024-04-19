package co.unicauca.taskhunters.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.unicauca.taskhunters.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * from tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Query("SELECT * from tasks ORDER BY title ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * from tasks t Where t.type == 'DAILY' ORDER BY title ASC")
    fun getAllDailies(): Flow<List<Task>>

    @Query("SELECT * from tasks t Where t.type == 'TODO' ORDER BY title ASC")
    fun getAllToDoS(): Flow<List<Task>>
}