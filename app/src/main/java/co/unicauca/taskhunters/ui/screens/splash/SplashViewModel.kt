package co.unicauca.taskhunters.ui.screens.splash

import co.unicauca.taskhunters.model.service.AccountService
import co.unicauca.taskhunters.ui.components.Screens
import co.unicauca.taskhunters.ui.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
) : AppViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(Screens.HomeScreen.name, Screens.SplashScreen.name)
        else openAndPopUp(Screens.LogInScreen.name, Screens.SplashScreen.name)
    }
}