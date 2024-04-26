package co.unicauca.taskhunters.data


/**
 * App container for Dependency injection.
 */
/*interface AppContainer {
    val tasksRepository: TasksRepository
}

/**
 * [AppContainer] implementation that provides instance of OfflineTasksRepository
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for TasksRepository
     */
    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepository(TaskDatabase.getDatabase(context).taskDao())
    }
}*/