package co.unicauca.taskhunters.ui.screens.register

import androidx.lifecycle.ViewModel
import co.unicauca.taskhunters.ui.common.ext.isValidEmail
import co.unicauca.taskhunters.ui.common.ext.isValidPassword
import co.unicauca.taskhunters.ui.common.ext.passwordMatches
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import co.unicauca.taskhunters.R.string as AppText

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    var uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val username
        get() = uiState.value.username
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onUsernameChange(newValue: String) {
        _uiState.update { it.copy(username = newValue) }
    }

    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    fun onConfirmPasswordChange(newValue: String) {
        _uiState.update { it.copy(confirmPassword = newValue) }
    }

    fun onClearFieldClick(property: String) {
        _uiState.value = when (property) {
            "username" -> _uiState.value.copy(username = "")
            "email" -> _uiState.value.copy(email = "")
            "password" -> _uiState.value.copy(password = "")
            "confirm password" -> _uiState.value.copy(confirmPassword = "")
            else -> _uiState.value
        }
    }

    fun onRegisterClick(goToHome: () -> Unit) {
        if (username.isBlank()) {
            SnackBarManager.showMessage(AppText.username_error)
            return
        }

        if (!email.isValidEmail()) {
            SnackBarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackBarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.confirmPassword)) {
            SnackBarManager.showMessage(AppText.password_match_error)
            return
        }

        goToHome()
    }
}