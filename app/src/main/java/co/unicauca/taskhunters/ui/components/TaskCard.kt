package co.unicauca.taskhunters.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import co.unicauca.taskhunters.ui.theme.GreenCardTask
import co.unicauca.taskhunters.ui.theme.OrangeCardTask
enum class TaskType { DAILY, TODO }
data class Task(val title: String, val body: String = "", val taskType: TaskType)

@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier) {
    var bgColor = OrangeCardTask
    if (task.taskType == TaskType.TODO) {
        bgColor = GreenCardTask
    }
    Row(
        modifier = modifier
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