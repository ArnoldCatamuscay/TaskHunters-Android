package co.unicauca.taskhunters.di

import co.unicauca.taskhunters.data.OfflineTasksRepository
import co.unicauca.taskhunters.data.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: OfflineTasksRepository): TasksRepository
}