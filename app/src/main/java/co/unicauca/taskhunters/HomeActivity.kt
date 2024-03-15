package co.unicauca.taskhunters

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import co.unicauca.taskhunters.ui.theme.GreenCardTask
import co.unicauca.taskhunters.ui.theme.OrangeCardTask
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

enum class TaskType { DAILY, TODO }
data class Task(val title: String, val body: String = "", val taskType: TaskType)

val taskList = listOf(
    Task(title = "Do Homework", taskType = TaskType.DAILY),
    Task(title = "To Do Project", body = "Meeting with my friends", taskType = TaskType.TODO),
)



@Composable
fun TasksList(messages: List<Task>) {
    LazyColumn {
        items(messages) { message ->
            TaskCard(message)
        }
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

@Composable
fun TaskCard(task: Task) {
    var bgColor = OrangeCardTask
    if (task.taskType == TaskType.TODO) {
        bgColor = GreenCardTask
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.7f))
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(bgColor)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onDraw = {
                    if (task.taskType == TaskType.TODO) {
                        drawCircle(Color.LightGray, radius = size.minDimension / 1.5f)
                    } else {
                        val roundedPolygon = RoundedPolygon(
                            numVertices = 4,
                            radius = size.minDimension / 1,
                            centerX = size.width / 2,
                            centerY = size.height / 2,
                            rounding = CornerRounding(
                                size.minDimension / 10f,
                                smoothing = 0.1f
                            )
                        )
                        val roundedPolygonPath = roundedPolygon
                            .toPath()
                            .asComposePath()
                        drawPath(roundedPolygonPath, color = Color.LightGray)
                    }

                }
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column {
            Text(
                text = task.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(20.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = task.body,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(28.dp)
            )
        }
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
fun RewardsCard(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        val recentRewardsList = listOf(
            R.drawable.silver_sword_image,
            R.drawable.wooden_shield_image,
            R.drawable.boots_image
        )

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
/*
@Preview
@Composable
fun TasksPreview() {
    TaskHuntersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TasksList(messages = taskList)
        }
    }
}*/

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home() {

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
    Scaffold(
        topBar = {
            /*TODO Search bar*/
            SearchBar(modifier = Modifier.padding(8.dp))
        },
        bottomBar = {
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            CharacterInfo(
                health = 1f,
                exp = 0.3f,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
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
            TasksList(messages = taskList)
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
            RewardsCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TaskHuntersTheme {
        Home()
    }
}