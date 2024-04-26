package co.unicauca.taskhunters.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarManager
import co.unicauca.taskhunters.ui.common.snackbar.SnackBarMessage.Companion.toSnackBarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {
    fun launchCatching(snackBar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackBar) {
                    SnackBarManager.showMessage(throwable.toSnackBarMessage())
                }
                Log.d(ERROR_TAG, throwable.message.orEmpty())
            },
            block = block
        )

    companion object {
        const val ERROR_TAG = "TASK HUNTERS APP ERROR"
    }
}