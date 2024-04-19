package co.unicauca.taskhunters.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.TaskHuntersAppState
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
import co.unicauca.taskhunters.ui.components.BOTTOM_NAV_ITEMS
import co.unicauca.taskhunters.ui.components.BottomNavBar
import co.unicauca.taskhunters.ui.components.FABScreensList
import co.unicauca.taskhunters.ui.components.NavigationDrawerContent
import co.unicauca.taskhunters.ui.components.NavigationGraph
import co.unicauca.taskhunters.ui.components.Screens
import co.unicauca.taskhunters.ui.screens.home.HomeViewModel
import co.unicauca.taskhunters.ui.screens.tasks.DailiesViewModel
import co.unicauca.taskhunters.ui.screens.tasks.EditTasksViewModel
import co.unicauca.taskhunters.ui.screens.tasks.ToDoSViewModel
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun TaskHuntersApp() {
    val appState = rememberAppState()
    val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val dailiesViewModel: DailiesViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val toDoSViewModel: ToDoSViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val editTaskViewModel: EditTasksViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerContent(
                onClickItem = { route -> appState.navController.navigate(route) },
                drawerState = appState.drawerState,
                scope = coroutineScope
            )
        },
        drawerState = appState.drawerState
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = appState.snackBarHostState,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackBarData ->
                        Snackbar(snackBarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                    }
                )
            },
            floatingActionButton = {
                if (currentRoute in FABScreensList) {
                    FloatingActionButton(
                        onClick = {
                            if (currentRoute == Screens.DailiesScreen.name) {
                                appState.navController.navigate(Screens.CreateDailyScreen.name)
                            } else if (currentRoute == Screens.ToDoScreen.name) {
                                appState.navController.navigate(Screens.CreateToDoScreen.name)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Create new task"
                            )
                            Text(text = stringResource(id = R.string.fab_create_task))
                        }
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                BottomNavBar(
                    BOTTOM_NAV_ITEMS,
                    onClickItem = { route -> appState.navController.navigate(route) }
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavigationGraph(
                    appState = appState,
                    scope = coroutineScope,
                    homeViewModel = homeViewModel,
                    dailiesViewModel = dailiesViewModel,
                    toDoSViewModel = toDoSViewModel,
                    editTasksViewModel = editTaskViewModel
                )
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(
        snackBarHostState,
        navController,
        drawerState,
        snackBarManager,
        resources,
        coroutineScope
    ) {
        TaskHuntersAppState(
            snackBarHostState,
            navController,
            drawerState,
            snackBarManager,
            resources,
            coroutineScope
        )
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Preview
@Composable
fun PreviewTaskHuntersApp() {
    TaskHuntersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TaskHuntersApp()
        }
    }
}