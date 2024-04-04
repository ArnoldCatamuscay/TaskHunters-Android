package co.unicauca.taskhunters.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import co.unicauca.taskhunters.ui.theme.TaskHuntersTheme

@Composable
fun RewardsScreen(modifier: Modifier = Modifier) {
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
        //Recent rewards
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = "Rewards",
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
        items(recentRewardsList) { resourceId ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = null,
                    modifier = Modifier
                        //.fillMaxSize()
                        .size(60.dp)
                        .align(Alignment.TopCenter)
                )
                Image(
                    painter = painterResource(R.drawable.coin_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.BottomStart)
                        .offset(x = (15).dp, y = (-2).dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "25",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.BottomCenter),
                    color = Color.Black
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RewardsPreview() {
    TaskHuntersTheme {
        RewardsScreen()
    }
}