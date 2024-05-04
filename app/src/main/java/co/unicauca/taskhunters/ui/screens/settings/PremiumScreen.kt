package co.unicauca.taskhunters.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.unicauca.taskhunters.R
@Composable
fun PremiumScreen(onBack: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        MyCustomScreen(onBack)
        RadioButtonGroup()
    }
}

@Composable
fun MyCustomScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
    ) {
        // Botón "X" en la esquina superior derecha
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            // Círculo blanco con icono de corona
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .background(Color.White)){
                Image(
                    modifier = Modifier
                        .align(Alignment.Center),
                    painter = painterResource(R.drawable.ic_crown),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Texto "Eliminar anuncios" debajo del círculo
            Text(
                text = stringResource(R.string.verpremium),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,

                )
        }
    }
}
@Composable
fun RadioButtonGroup() {
    val options = listOf(
        Pair(stringResource(R.string.oferprice1), stringResource(R.string.ofername1)),
        Pair(stringResource(R.string.oferprice2), stringResource(R.string.ofername2)),
        Pair(stringResource(R.string.oferprice3), stringResource(R.string.ofername3))
    )

    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.forEach { option ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option }
                    )
                    .padding(8.dp)
                    .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
            ) {
                Row {
                    RadioButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp),
                        selected = (option == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Column {
                        Text(
                            text = option.second,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = option.first,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(end = 4.dp)

                        )
                    }

                }


            }
        }
        Button(
            onClick = { /* Acción al hacer clic en el botón */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.buttonpremium))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}