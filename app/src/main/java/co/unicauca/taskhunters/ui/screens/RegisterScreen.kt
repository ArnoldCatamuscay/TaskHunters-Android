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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.register_image),
                contentDescription = "Register image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                //.padding(top = 8.dp)
                //.clip(shape = RoundedCornerShape(48.dp))
            )
            IconButton(
                onClick = { /* Return */ },
                modifier = Modifier
                    .padding(12.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        InputField(placeholder = "Username")
        InputField(placeholder = "Email")
        InputField(
            placeholder = "Password",
            visualTransformation = PasswordVisualTransformation()
        )
        InputField(
            placeholder = "Confirm password",
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Register")
        }
    }
}

@Composable
fun InputField(
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        visualTransformation = visualTransformation,
        onValueChange = {
            text = it
        },
        placeholder = { Text(text = placeholder) },
        supportingText = { Text(text = "Supporting text") },
        trailingIcon = {
            if (text.text.isNotEmpty()) {
                IconButton(
                    onClick = { /* Clear */ },
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear field"
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp
            )
    )
    Spacer(modifier = Modifier.padding(8.dp))
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        RegisterScreen()
    }
}