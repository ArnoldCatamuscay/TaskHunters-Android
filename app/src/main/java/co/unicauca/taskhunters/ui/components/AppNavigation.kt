package co.unicauca.taskhunters.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.unicauca.taskhunters.TaskHuntersAppState
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.ui.screens.tasks.DailiesScreen
import co.unicauca.taskhunters.ui.screens.tasks.DailiesViewModel
import co.unicauca.taskhunters.ui.screens.tasks.EditTaskScreen
import co.unicauca.taskhunters.ui.screens.tasks.EditTasksViewModel
import co.unicauca.taskhunters.ui.screens.home.HomeScreen
import co.unicauca.taskhunters.ui.screens.home.HomeViewModel
import co.unicauca.taskhunters.ui.screens.register.RegisterScreen
import co.unicauca.taskhunters.ui.screens.rewards.RewardsScreen
import co.unicauca.taskhunters.ui.screens.tasks.ToDoSViewModel
import co.unicauca.taskhunters.ui.screens.tasks.ToDoScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
    appState: TaskHuntersAppState,
    homeViewModel: HomeViewModel,
    dailiesViewModel: DailiesViewModel,
    toDoSViewModel: ToDoSViewModel,
    editTasksViewModel: EditTasksViewModel,
    scope: CoroutineScope
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(route = Screens.HomeScreen.name) {
            HomeScreen(
                onOpenDrawer = {
                    scope.launch {
                        appState.drawerState.open()
                    }
                },
                coroutineScope = scope,
                homeViewModel = homeViewModel
            )
        }
        composable(route = Screens.DailiesScreen.name) {
            DailiesScreen(
                coroutineScope = scope,
                dailiesViewModel = dailiesViewModel,
                goToEdit = {
                    val gson: Gson = GsonBuilder().create()
                    val taskJson = gson.toJson(it)
                    /* Replacing {task} with taskJson */
                    appState.navController.navigate(
                        "${Screens.EditDailyScreen.name}/{task}" //Just modify your route accordingly
                            .replace(
                                oldValue = "{task}",
                                newValue = taskJson
                            )
                    )
                }
            )
            //appState.navController.navigate(Screens.EditDailyScreen.name)
        }
        composable(route = Screens.ToDoScreen.name) {
            ToDoScreen(
                coroutineScope = scope,
                toDoSViewModel = toDoSViewModel
            )
        }
        composable(route = Screens.RewardsScreen.name) {
            RewardsScreen()
        }
        composable(route = Screens.RegisterScreen.name) {
            RegisterScreen(
                goBack = { appState.navController.navigateUp() },
                goToHome = { appState.navController.navigate(Screens.HomeScreen.name) }
            )
        }
        composable(route = Screens.SettingsScreen.name) {

        }
        composable(route = Screens.CreateDailyScreen.name) {
            /*EditTaskScreen(
                //task = it,
                isDaily = true,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )*/
        }
        composable(route = Screens.CreateToDoScreen.name) {
           /* EditTaskScreen(
                isDaily = false,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )*/
        }
        composable(route = "${Screens.EditDailyScreen.name}/{task}") { navBackStackEntry ->
            // Creating gson object
            val gson: Gson = GsonBuilder().create()
            /* Extracting the user object json from the route */
            val taskJson = navBackStackEntry.arguments?.getString("task")
            // Convert json string to the User data class object
            val taskObject = gson.fromJson(taskJson, Task::class.java)
            //DetailScreen(user = userObject)
            Log.d("taskObject",taskObject.toString())
            EditTaskScreen(
                task = taskObject,
                isDaily = true,
                isCreated = true,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )
        }
        composable(route = Screens.EditToDoScreen.name) {
            /*EditTaskScreen(
                isDaily = false,
                isCreated = true,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )*/
        }
    }
}
