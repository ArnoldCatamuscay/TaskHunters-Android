package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R

@Composable
fun TaskFAB(
    currentRoute: String,
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val destination = if (currentRoute == Screens.DailiesScreen.name)
        Screens.CreateDailyScreen.name
    else
        Screens.CreateToDoScreen.name

    FloatingActionButton(
        modifier = modifier,
        onClick = {
            onClickButton.invoke(destination)
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.fab_create_task_desc)
            )
            Text(text = stringResource(id = R.string.fab_create_task))
        }
    }
}