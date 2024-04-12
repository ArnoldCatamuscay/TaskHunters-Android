package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.TaskHuntersAppState
import co.unicauca.taskhunters.ui.screens.HomeScreen
import co.unicauca.taskhunters.ui.screens.RegisterScreen
import co.unicauca.taskhunters.ui.screens.RewardsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
    appState: TaskHuntersAppState,
    scope: CoroutineScope
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(route = Screens.HomeScreen.name) {
            HomeScreen(onOpenDrawer = {
                scope.launch {
                    appState.drawerState.open()
                }
            })
        }
        composable(route = Screens.DailiesScreen.name) {

        }
        composable(route = Screens.ToDoScreen.name) {

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

@Composable
fun NavigationDrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    ModalDrawerSheet {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(16.dp)
        )
        DRAWER_ITEMS.forEachIndexed { index, item ->
            NavigationDrawerItem(
                selected = index == selectedItemIndex,
                label = { Text(text = stringResource(id = item.title)) },
                onClick = {
                    selectedItemIndex = index
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex)
                            item.selectedIcon
                        else item.unselectedIcon,
                        contentDescription = stringResource(id = item.title)
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(text = item.badgeCount.toString())
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    onMenuClick: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    SearchBar(
        query = searchQuery, // Pass the state variable
        onQueryChange = { newQuery -> searchQuery = newQuery }, // Update on change
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                stringResource(R.string.placeholder_search),
                modifier = Modifier.padding(start = 5.dp)
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(end = 15.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    items: List<NavigationItem>,
    onClickButton: (String) -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedIndex = index
                    onClickButton.invoke(item.route)
                },
                label = { Text(text = stringResource(id = item.title)) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = stringResource(id = item.title)
                        )
                    }
                }
            )
        }
    }
}