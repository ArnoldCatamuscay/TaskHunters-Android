package co.unicauca.taskhunters

import android.app.Application
import co.unicauca.taskhunters.data.AppContainer
import co.unicauca.taskhunters.data.AppDataContainer

class TaskHuntersApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}