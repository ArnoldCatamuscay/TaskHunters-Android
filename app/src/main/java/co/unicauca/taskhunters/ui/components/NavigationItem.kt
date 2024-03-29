package co.unicauca.taskhunters.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screens {
    HomeScreen,
    DailiesScreen,
    ToDoScreen,
    RewardsScreen,
    SettingsScreen,
    RegisterScreen
}

data class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val DRAWER_ITEMS = listOf(
    NavigationItem(
        route = Screens.HomeScreen.name,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.DailiesScreen.name,
        title = "Dailies",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.ToDoScreen.name,
        title = "To Do's",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.RewardsScreen.name,
        title = "Rewards",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        hasNews = true,
        badgeCount = 3
    ),
    NavigationItem(
        route = Screens.SettingsScreen.name,
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.RegisterScreen.name,
        title = "Register",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
    ),
)

val BOTTOM_NAV_ITEMS = listOf(
    NavigationItem(
        route = Screens.HomeScreen.name,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.DailiesScreen.name,
        title = "Dailies",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.ToDoScreen.name,
        title = "To Do's",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.RewardsScreen.name,
        title = "Rewards",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        hasNews = true,
        badgeCount = 3
    ),
)