package co.unicauca.taskhunters.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.unicauca.taskhunters.TaskHuntersAppState
import co.unicauca.taskhunters.ui.screens.DailiesScreen
import co.unicauca.taskhunters.ui.screens.HomeScreen
import co.unicauca.taskhunters.ui.screens.RegisterScreen
import co.unicauca.taskhunters.ui.screens.RewardsScreen
import co.unicauca.taskhunters.ui.screens.TasksViewModel
import co.unicauca.taskhunters.ui.screens.ToDoScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
    appState: TaskHuntersAppState,
    taskViewModel: TasksViewModel,
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
                tasksViewModel = taskViewModel
            )
        }
        composable(route = Screens.DailiesScreen.name) {
            DailiesScreen(tasksViewModel = taskViewModel)

        }
        composable(route = Screens.ToDoScreen.name) {
            ToDoScreen()
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
    }
}
