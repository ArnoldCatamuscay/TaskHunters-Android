package co.unicauca.taskhunters

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.ImageVector
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
fun TaskCard(task: Task) {
    var bgColor = OrangeCardTask
    if (task.taskType == TaskType.TODO) {
        bgColor = GreenCardTask
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
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
        TasksList(messages = taskList)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TaskHuntersTheme {
        Home()
    }
}