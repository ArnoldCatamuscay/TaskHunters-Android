package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import co.unicauca.taskhunters.model.Task
import co.unicauca.taskhunters.model.TaskType
import co.unicauca.taskhunters.ui.theme.GreenCardTask
import co.unicauca.taskhunters.ui.theme.OrangeCardTask

@Composable
fun TaskCard(
    task: Task,
    onChecked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.7f))
    ) {
        TaskCheckBox(taskType = task.type, onClick = onChecked)
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
                text = task.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(28.dp)
            )
        }
    }
}

@Composable
fun TaskCheckBox(
    taskType: TaskType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var bgColor = OrangeCardTask
    if (taskType == TaskType.TODO) {
        bgColor = GreenCardTask
    }
    Box(
        modifier = modifier
            .size(48.dp)
            .background(bgColor)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable { onClick() },
            onDraw = {
                if (taskType == TaskType.TODO) {
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
}