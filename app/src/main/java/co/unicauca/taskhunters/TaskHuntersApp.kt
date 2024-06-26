package co.unicauca.taskhunters

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
import co.unicauca.taskhunters.ui.components.BOTTOM_NAV_ITEMS
import co.unicauca.taskhunters.ui.components.BottomNavBar
import co.unicauca.taskhunters.ui.components.FABScreensList
import co.unicauca.taskhunters.ui.components.NavigationDrawerContent
import co.unicauca.taskhunters.ui.components.NavigationGraph
import co.unicauca.taskhunters.ui.components.TaskFAB
import co.unicauca.taskhunters.ui.components.noBottomScreensList
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun TaskHuntersApp() {
    val appState = rememberAppState()
    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerContent(
                onClickItem = { route -> appState.navigate(route) },
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
                if (currentRoute != null && currentRoute in FABScreensList)
                    TaskFAB(
                        currentRoute = currentRoute,
                        onClickButton = { route -> appState.navigate(route) }
                    )
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                if (currentRoute != null && currentRoute !in noBottomScreensList)
                    BottomNavBar(
                        BOTTOM_NAV_ITEMS,
                        onClickItem = { route -> appState.navigate(route) }
                    )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavigationGraph(appState = appState, scope = coroutineScope)
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