package co.unicauca.taskhunters.ui.screens.login

import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.service.AccountService
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
import co.unicauca.taskhunters.ui.components.Screens
import co.unicauca.taskhunters.ui.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
): AppViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    var uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    fun onClearFieldClick(property: String) {
        _uiState.value = when (property) {
            "email" -> _uiState.value.copy(email = "")
            "password" -> _uiState.value.copy(password = "")
            else -> _uiState.value
        }
    }

    fun onSignInClick(navigateAndPopUp: (String, String) -> Unit) {
        if (email.isBlank()) {
            SnackBarManager.showMessage(R.string.email_empty)
            return
        }

        if (password.isBlank()) {
            SnackBarManager.showMessage(R.string.password_empty)
            return
        }

        launchCatching {
            accountService.signIn(email, password)
            navigateAndPopUp(Screens.RewardsScreen.name, Screens.LogInScreen.name)
        }
    }

    fun onSignUpClick(navigateAndPopUp: (String, String) -> Unit) {
        navigateAndPopUp(Screens.RegisterScreen.name, Screens.LogInScreen.name)
    }
}