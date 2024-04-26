package co.unicauca.taskhunters.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.unicauca.taskhunters.TaskHuntersAppState
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import co.unicauca.taskhunters.ui.screens.account_center.AccountCenterScreen
import co.unicauca.taskhunters.ui.screens.home.HomeScreen
import co.unicauca.taskhunters.ui.screens.login.LoginScreen
import co.unicauca.taskhunters.ui.screens.register.RegisterScreen
import co.unicauca.taskhunters.ui.screens.rewards.RewardsScreen
import co.unicauca.taskhunters.ui.screens.splash.SplashScreen
import co.unicauca.taskhunters.ui.screens.tasks.DailiesScreen
import co.unicauca.taskhunters.ui.screens.tasks.EditTaskScreen
import co.unicauca.taskhunters.ui.screens.tasks.ToDoScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
    appState: TaskHuntersAppState,
    scope: CoroutineScope
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screens.SplashScreen.name
    ) {
        composable(route = Screens.HomeScreen.name) {
            HomeScreen(
                onOpenDrawer = {
                    scope.launch {
                        appState.drawerState.open()
                    }
                },
                coroutineScope = scope,
                restartApp = { route -> appState.navigate(route) },
                goToEdit = {
                    val gson: Gson = GsonBuilder().create()
                    val taskJson = gson.toJson(it)
                    /* Replacing {task} with taskJson */
                    appState.navController.navigate(
                        if (it.type == TaskType.DAILY) {
                            "${Screens.EditDailyScreen.name}/{task}" //Just modify your route accordingly
                                .replace(
                                    oldValue = "{task}",
                                    newValue = taskJson
                                )
                        } else {
                            "${Screens.EditToDoScreen.name}/{task}" //Just modify your route accordingly
                                .replace(
                                    oldValue = "{task}",
                                    newValue = taskJson
                                )
                        }

                    )
                }
            )
        }
        composable(route = Screens.DailiesScreen.name) {
            DailiesScreen(
                coroutineScope = scope,
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
        }
        composable(route = Screens.ToDoScreen.name) {
            ToDoScreen(
                coroutineScope = scope,
                goToEdit = {
                    val gson: Gson = GsonBuilder().create()
                    val taskJson = gson.toJson(it)
                    /* Replacing {task} with taskJson */
                    appState.navController.navigate(
                        "${Screens.EditToDoScreen.name}/{task}" //Just modify your route accordingly
                            .replace(
                                oldValue = "{task}",
                                newValue = taskJson
                            )
                    )
                }
            )
        }
        composable(route = Screens.RewardsScreen.name) {
            RewardsScreen()
        }
        composable(route = Screens.RegisterScreen.name) {
            RegisterScreen(
                navigateAndPopUp = { route, popUp ->
                    appState.navigateAndPopUp(route, popUp)
                }
            )
        }
        composable(route = Screens.SettingsScreen.name) {

        }
        composable(route = Screens.CreateDailyScreen.name) {
            EditTaskScreen(
                isDaily = true,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
            )
        }
        composable(route = Screens.CreateToDoScreen.name) {
            EditTaskScreen(
                isDaily = false,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
            )
        }
        composable(route = "${Screens.EditDailyScreen.name}/{task}") { navBackStackEntry ->
            // Creating gson object
            val gson: Gson = GsonBuilder().create()
            /* Extracting the user object json from the route */
            val taskJson = navBackStackEntry.arguments?.getString("task")
            // Convert json string to the User data class object
            val taskObject = gson.fromJson(taskJson, Task::class.java)
            //DetailScreen(user = userObject)
            EditTaskScreen(
                task = taskObject,
                isDaily = true,
                isCreated = true,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope
            )
        }
        composable(route = "${Screens.EditToDoScreen.name}/{task}") { navBackStackEntry ->
            // Creating gson object
            val gson: Gson = GsonBuilder().create()
            /* Extracting the user object json from the route */
            val taskJson = navBackStackEntry.arguments?.getString("task")
            // Convert json string to the User data class object
            val taskObject = gson.fromJson(taskJson, Task::class.java)
            EditTaskScreen(
                task = taskObject,
                isDaily = false,
                isCreated = true,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope
            )
        }
        composable(route = Screens.SplashScreen.name) {
            SplashScreen(navigateAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            })
        }
        composable(route = Screens.LogInScreen.name) {
            LoginScreen(navigateAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            })
        }
        composable(route = Screens.AccountScreen.name) {
            AccountCenterScreen(restartApp = { route -> appState.navigate(route) })
        }
    }
}
