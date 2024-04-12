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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R.string as AppText
import co.unicauca.taskhunters.R.drawable as AppDrawable
import androidx.lifecycle.viewmodel.compose.viewModel
import co.unicauca.taskhunters.ui.common.composable.InputField

@Composable
fun RegisterScreen(
    goBack: () -> Unit,
    goToHome: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel()
) {
    // Whenever there's a change in the uiState value, a recomposition occurs
    // for the composable function with registerUiState
    val registerUiState by registerViewModel.uiState.collectAsState()

    RegisterScreenContent(
        uiState = registerUiState,
        onUsernameChange = registerViewModel::onUsernameChange,
        onEmailChange = registerViewModel::onEmailChange,
        onPasswordChange = registerViewModel::onPasswordChange,
        onConfirmPasswordChange = registerViewModel::onConfirmPasswordChange,
        onClearFieldClick = registerViewModel::onClearFieldClick,
        onReturnClick = { goBack() },
        onRegisterClick = { registerViewModel.onRegisterClick(goToHome) },
    )
}

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onClearFieldClick: (String) -> Unit,
    onReturnClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(AppDrawable.register_image),
                contentDescription = "Register image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
            )
            IconButton(
                onClick = { onReturnClick() },
                modifier = Modifier
                    .padding(12.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))

        InputField(
            placeholder = AppText.username_placeholder,
            value = uiState.username,
            onNewValue = onUsernameChange,
            onClearClick = { onClearFieldClick("username") },
        )

        InputField(
            placeholder = AppText.email_placeholder,
            value = uiState.email,
            onNewValue = onEmailChange,
            onClearClick = { onClearFieldClick("email") },
        )

        InputField(
            placeholder = AppText.password_placeholder,
            value = uiState.password,
            onNewValue = onPasswordChange,
            onClearClick = { onClearFieldClick("password") },
            visualTransformation = PasswordVisualTransformation()
        )

        InputField(
            placeholder = AppText.confirm_password_placeholder,
            value = uiState.confirmPassword,
            onNewValue = onConfirmPasswordChange,
            onClearClick = { onClearFieldClick("confirm password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = { onRegisterClick() }) {
            Text(text = stringResource(AppText.nav_register_text))
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        RegisterScreen(goBack = {}, goToHome = {})
    }
}