package co.unicauca.taskhunters.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.unicauca.taskhunters.TaskHuntersAppState
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
                dailiesViewModel = dailiesViewModel
            )

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
            EditTaskScreen(
                isDaily = true,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )
        }
        composable(route = Screens.CreateToDoScreen.name) {
            EditTaskScreen(
                isDaily = false,
                isCreated = false,
                goBack = { appState.navController.navigateUp() },
                coroutineScope = scope,
                editTasksViewModel = editTasksViewModel
            )
        }
    }
}
