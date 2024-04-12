package co.unicauca.taskhunters.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.data.recentRewardsList
import co.unicauca.taskhunters.data.taskList
import co.unicauca.taskhunters.ui.components.TaskCard
import co.unicauca.taskhunters.ui.components.TopSearchBar
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            TopSearchBar(drawerState = drawerState, scope = scope)
        }
        //Character info
        item(span = { GridItemSpan(maxLineSpan) }) {
            CharacterInfo(
                health = 1f,
                exp = 0.3f
            )
        }
        //Pending tasks
        item(span = { GridItemSpan(maxLineSpan) }) {
            PendingTasksTitle();
        }
        items(
            taskList,
            span = { GridItemSpan(maxLineSpan) }
        ) { message ->
            TaskCard(message)
        }
        //Recent rewards
        item(span = { GridItemSpan(maxLineSpan) }) {
            RecentRewardsTitle()
        }
        items(recentRewardsList) { reward ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray.copy(alpha = 0.7f))
            ) {
                Image(
                    painter = painterResource(id = reward.imageId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CharacterInfo(
    health: Float,
    exp: Float,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(225.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray.copy(alpha = 0.7f))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.character_image),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                LinearProgressIndicator(
                    progress = health,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Health: ${health.times(100).toInt()}%",
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = exp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Exp: ${exp.times(100).toInt()}%",
                )
            }
        }
    }
}

@Composable
fun PendingTasksTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.pending_tasks_text),
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

@Composable
fun RecentRewardsTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.recent_rewards_text),
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TaskHuntersTheme {
        //HomeScreen()
    }
}