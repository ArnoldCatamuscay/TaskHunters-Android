package co.unicauca.taskhunters.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import co.unicauca.taskhunters.R

enum class Screens {
    SplashScreen,
    HomeScreen,
    DailiesScreen,
    ToDoScreen,
    RewardsScreen,
    SettingsScreen,
    LogInScreen,
    RegisterScreen,
    AboutUsScreen,
    CreateDailyScreen,
    CreateToDoScreen,
    EditDailyScreen,
    EditToDoScreen,
    AccountScreen,
    PremiumScreen,
}

val FABScreensList = listOf(
    Screens.DailiesScreen.toString(),
    Screens.ToDoScreen.toString()
)

val noBottomScreensList = listOf(
    Screens.SplashScreen.name,
    Screens.LogInScreen.name,
    Screens.RegisterScreen.name,
    Screens.CreateDailyScreen.name,
    Screens.CreateToDoScreen.name,
    Screens.EditDailyScreen.name,
    Screens.EditToDoScreen.name,
)

data class NavigationItem(
    val route: String,
    val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val DRAWER_ITEMS = listOf(
    NavigationItem(
        route = Screens.HomeScreen.name,
        title = R.string.nav_home_text,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.DailiesScreen.name,
        title = R.string.nav_dailies_text,
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.ToDoScreen.name,
        title = R.string.nav_to_do_s_text,
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.RewardsScreen.name,
        title = R.string.nav_rewards_text,
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        hasNews = true,
        badgeCount = 3
    ),
    NavigationItem(
        route = Screens.SettingsScreen.name,
        title = R.string.nav_settings_text,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.AccountScreen.name,
        title = R.string.nav_account_text,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.AboutUsScreen.name,
        title = R.string.nav_aboutus_text,
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        hasNews = false,
    )
)

val BOTTOM_NAV_ITEMS = listOf(
    NavigationItem(
        route = Screens.HomeScreen.name,
        title = R.string.nav_home_text,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.DailiesScreen.name,
        title = R.string.nav_dailies_text,
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
    ),
    NavigationItem(
        route = Screens.ToDoScreen.name,
        title = R.string.nav_to_do_s_text,
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        hasNews = false
    ),
    NavigationItem(
        route = Screens.RewardsScreen.name,
        title = R.string.nav_rewards_text,
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        hasNews = true,
        badgeCount = 3
    ),
)