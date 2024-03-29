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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.data.recentRewardsList
import co.unicauca.taskhunters.data.taskList
import co.unicauca.taskhunters.ui.components.Task
import co.unicauca.taskhunters.ui.components.TaskCard
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CharacterInfo(
            health = 1f,
            exp = 0.3f,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
        )
        PendingTasks(messages = taskList, modifier = Modifier.weight(1f))
        RecentRewards(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CharacterInfo(
    health: Float,
    exp: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(225.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray.copy(alpha = 0.7f))
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

@Composable
fun PendingTasks(messages: List<Task>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Pending Tasks",
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
        LazyColumn {
            items(messages) { message ->
                TaskCard(message)
            }
        }
    }
}

@Composable
fun RecentRewards(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Recent Rewards",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(recentRewardsList) { resourceId ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray.copy(alpha = 0.7f))
                ) {
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TaskHuntersTheme {
        HomeScreen()
    }
}