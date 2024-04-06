package co.unicauca.taskhunters.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import co.unicauca.taskhunters.ui.components.BOTTOM_NAV_ITEMS
import co.unicauca.taskhunters.ui.components.BottomNavBar
import co.unicauca.taskhunters.ui.components.NavigationDrawerContent
import co.unicauca.taskhunters.ui.components.NavigationGraph
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

@Composable
fun TaskHuntersApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerContent(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            /*topBar = {
                //MySearchBar(modifier = Modifier.padding(8.dp))
                TopSearchBar(drawerState = drawerState, scope = scope)
            },*/
            bottomBar = {
                BottomNavBar(
                    BOTTOM_NAV_ITEMS,
                    onClickButton = { route -> navController.navigate(route) }
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavigationGraph(navController, drawerState=drawerState, scope=scope)
            }
        }
    }
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