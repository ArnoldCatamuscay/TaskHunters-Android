package co.unicauca.taskhunters.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.ui.components.CharacterInfo
import co.unicauca.taskhunters.ui.components.TaskCard
import co.unicauca.taskhunters.ui.components.TopSearchBar
import co.unicauca.taskhunters.ui.screens.rewards.recentRewardsList
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onOpenDrawer: () -> Unit,
    coroutineScope: CoroutineScope,
    goToEdit: (Task) -> Unit,
    restartApp: (String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) { homeViewModel.initialize(restartApp) }
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            TopSearchBar(onMenuClick = onOpenDrawer)
        }
        //Character info
        item(span = { GridItemSpan(maxLineSpan) }) {
            CharacterInfo(health = 1f, exp = 0.3f)
        }
        //Pending tasks
        item(span = { GridItemSpan(maxLineSpan) }) {
            PendingTasksTitle()
        }
        items(
            homeUiState.taskList,
            span = { GridItemSpan(maxLineSpan) }
        ) { task ->
            TaskCard(
                task = task,
                onClickEdit = { goToEdit(task) },
                onChecked = {
                    coroutineScope.launch {
                        homeViewModel.taskChecked(task)
                    }
                }
            )
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
        //HomeScreen(onOpenDrawer = {})
    }
}