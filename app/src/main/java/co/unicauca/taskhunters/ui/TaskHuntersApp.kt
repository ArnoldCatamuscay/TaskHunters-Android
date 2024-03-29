package co.unicauca.taskhunters.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.ui.screens.HomeScreen
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    BottomNavigationItem(
        title = "Dailies",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
    ),
    BottomNavigationItem(
        title = "To Do's",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        hasNews = false
    ),
    BottomNavigationItem(
        title = "Rewards",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        hasNews = true,
        badgeCount = 3
    ),
)

@Composable
fun TaskHuntersApp() {
    Scaffold(
        topBar = { SearchBar(modifier = Modifier.padding(8.dp)) },
        bottomBar = { BottomNavBar(items) },
    ) {innerPadding ->
        HomeScreen(innerPadding = innerPadding)
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier.padding(start = 15.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(end = 15.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedContainerColor = Color.LightGray,
            focusedContainerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(30.dp),
        placeholder = {
            Text(
                stringResource(R.string.placeholder_search),
                modifier = Modifier.padding(start = 5.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(items: List<BottomNavigationItem>) {
    NavigationBar {
        items.forEachIndexed { _, item ->
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                label = { Text(text = item.title) },
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
                            contentDescription = item.title
                        )
                    }
                }
            )
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