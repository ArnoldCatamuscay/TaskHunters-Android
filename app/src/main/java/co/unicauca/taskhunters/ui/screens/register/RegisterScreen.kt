package co.unicauca.taskhunters.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.ui.common.composable.InputField
import co.unicauca.taskhunters.ui.theme.Purple40
import co.unicauca.taskhunters.R.string as AppText

@Composable
fun RegisterScreen(
    navigateAndPopUp: (String, String) -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val registerUiState by registerViewModel.uiState.collectAsState()

    RegisterScreenContent(
        uiState = registerUiState,
        onUsernameChange = registerViewModel::onUsernameChange,
        onEmailChange = registerViewModel::onEmailChange,
        onPasswordChange = registerViewModel::onPasswordChange,
        onConfirmPasswordChange = registerViewModel::onConfirmPasswordChange,
        onClearFieldClick = registerViewModel::onClearFieldClick,
        onRegisterClick = { registerViewModel.onRegisterClick(navigateAndPopUp) },
        onLogInClick = { registerViewModel.onLogInClick(navigateAndPopUp) }
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
    onRegisterClick: () -> Unit,
    onLogInClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(R.drawable.register_image),
            contentDescription = "Register image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 48.dp))
                .size(180.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp
        )
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
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onRegisterClick() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.nav_register_text).uppercase())
            }
        }
        ClickableText(
            text = AnnotatedString(stringResource(R.string.already_have_an_account)),
            modifier = Modifier
                .padding(20.dp),
            onClick = { onLogInClick() },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
        /*Spacer(modifier = Modifier.height(20.dp))
        Divider(Modifier.padding(start = 24.dp, end = 24.dp))
        Spacer(modifier = Modifier.height(20.dp))
        SocialMediaButton(
            onClick = {},
            text = stringResource(R.string.continue_with_google),
            icon = R.drawable.ic_google,
            color = Color(0xFFF1F1F1)
        )*/
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    Surface(
        //modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        RegisterScreenContent(
            uiState = RegisterUiState(),
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onClearFieldClick = {},
            onRegisterClick = { },
            onLogInClick = {},
        )
    }
}