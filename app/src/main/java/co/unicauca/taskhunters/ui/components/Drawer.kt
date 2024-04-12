package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.unicauca.taskhunters.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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