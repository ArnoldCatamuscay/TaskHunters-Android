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

    fun onAppStart(navigateAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser())
            navigateAndPopUp(Screens.HomeScreen.name, Screens.SplashScreen.name)
        else
            createAnonymousAccount(navigateAndPopUp)
    }

    private fun createAnonymousAccount(navigateAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.createAnonymousAccount()
            navigateAndPopUp(Screens.HomeScreen.name, Screens.SplashScreen.name)
        }
    }
}