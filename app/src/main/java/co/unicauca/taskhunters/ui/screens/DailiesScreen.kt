package co.unicauca.taskhunters.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.ui.components.CharacterInfo
import co.unicauca.taskhunters.ui.components.TaskCard
import co.unicauca.taskhunters.ui.components.TaskType
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

@Composable
fun DailiesScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        //Character info
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(modifier = Modifier.fillMaxWidth()) {
                CharacterInfo(
                    health = 1f,
                    exp = 0.3f,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        //Dailies List
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = stringResource(R.string.nav_dailies_text),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        items(
            tasksViewModel.tasks.filter { it.taskType == TaskType.DAILY },
            span = { GridItemSpan(maxLineSpan) }
        ) { message ->
            TaskCard(
                task = message,
                onChecked = { tasksViewModel.taskChecked(message) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailiesPreview() {
    TaskHuntersTheme {
        //DailiesScreen()
    }
}