package co.unicauca.taskhunters.ui.screens.tasks

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.ui.components.CharacterInfo
import co.unicauca.taskhunters.ui.components.TaskCard
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    goToEdit: (Task) -> Unit,
    toDoSViewModel: ToDoSViewModel
) {
    val toDoSUiState by toDoSViewModel.toDoSUiState.collectAsState()
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
        //To Do List
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = stringResource(R.string.nav_to_do_s_text),
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
            toDoSUiState.taskList,
            span = { GridItemSpan(maxLineSpan) }
        ) { task ->
            TaskCard(
                task = task,
                onClickEdit = { goToEdit(task) },
                onChecked = {
                    coroutineScope.launch {
                        toDoSViewModel.taskChecked(task)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    TaskHuntersTheme {
        //ToDoScreen()
    }
}