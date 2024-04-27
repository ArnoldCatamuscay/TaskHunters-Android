package co.unicauca.taskhunters.ui.screens.login

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
import androidx.compose.material3.Divider
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

@Composable
fun LoginScreen(
    navigateAndPopUp: (String, String) -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = loginUiState,
        onEmailChange = loginViewModel::onEmailChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onClearFieldClick = loginViewModel::onClearFieldClick,
        onLoginClick = { loginViewModel.onSignInClick(navigateAndPopUp) },
        //onGoogleClick = {},
        onRegisterClick = { loginViewModel.onSignUpClick(navigateAndPopUp) }
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClearFieldClick: (String) -> Unit,
    onLoginClick: () -> Unit,
    //onGoogleClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
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
            placeholder = R.string.email_placeholder,
            value = uiState.email,
            onNewValue = onEmailChange,
            onClearClick = { onClearFieldClick("email") },
        )

        InputField(
            placeholder = R.string.password_placeholder,
            value = uiState.password,
            onNewValue = onPasswordChange,
            onClearClick = { onClearFieldClick("password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { onLoginClick() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.btn_login).uppercase())
            }
        }

        /*Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            text = AnnotatedString(stringResource(R.string.forgot_password)),
            onClick = {},
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )*/
        //Spacer(modifier = Modifier.height(20.dp))
        //Text(text = "-------- o --------", style = TextStyle(color = Color.Gray))
        Divider(Modifier.padding(24.dp))

        //Spacer(modifier = Modifier.height(15.dp))
        /*SocialMediaButton(
            onClick = onGoogleClick,
            text = stringResource(R.string.continue_with_google),
            icon = R.drawable.ic_google,
            color = Color(0xFFF1F1F1)
        )*/
        ClickableText(
            text = AnnotatedString(stringResource(R.string.register_question)),
            //modifier = Modifier.padding(20.dp),
            onClick = { onRegisterClick() },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
}

/*@Composable
fun SocialMediaButton(onClick: () -> Unit, text: String, icon: Int, color: Color) {
    var click by remember { mutableStateOf(false) }
    Surface(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable { click = !click },
        shape = RoundedCornerShape(50),
        border = BorderStroke(
            width = 1.dp,
            color = if (icon == R.drawable.ic_incognito) color else Color.Gray
        ),
        color = color
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.size(24.dp),
                contentDescription = text,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = if (icon == R.drawable.ic_incognito) Color.White else Color.Black
            )
            click = true
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreenContent(
        uiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onClearFieldClick = {},
        onLoginClick = { },
        //onGoogleClick = {},
        onRegisterClick = {}
    )
}