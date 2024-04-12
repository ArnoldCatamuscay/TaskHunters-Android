package co.unicauca.taskhunters.ui.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

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