package co.unicauca.taskhunters.ui.screens.account_center

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.User
import java.util.Locale

@Composable
fun AccountCenterScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: AccountCenterViewModel = hiltViewModel()
){
    val user by viewModel.user.collectAsState(initial = User())
    val provider = user.provider.replaceFirstChar { it.titlecase(Locale.getDefault()) }

    Column {
        Card {
            Spacer(modifier = Modifier.padding(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                if (!user.isAnonymous) {
                    Text(text = "User email:")
                    Text(
                        text = user.email,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }

                // ⚠️This is for demonstration purposes only, it's not a common
                // practice to show the unique ID or account provider of an account⚠️
                Text(text = "User ID:")
                Text(
                    text = user.id,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Text(text = "Provider:")
                Text(
                    text = provider,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        if (user.isAnonymous) {
            AccountCenterCard(stringResource(R.string.btn_login), Icons.Filled.Face) {
                viewModel.onSignInClick(openScreen)
            }

            AccountCenterCard(stringResource(R.string.nav_register_text), Icons.Filled.AccountCircle) {
                viewModel.onSignUpClick(openScreen)
            }
        } else {
            ExitAppCard { viewModel.onSignOutClick(restartApp) }
            RemoveAccountCard { viewModel.onDeleteAccountClick(restartApp) }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AccountCenterCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onCardClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) { Text(title) }
            Icon(icon, contentDescription = "Icon")
        }
    }
}

@Composable
fun ExitAppCard(onSignOutClick: () -> Unit) {
    var showExitAppDialog by remember { mutableStateOf(false) }

    AccountCenterCard("Sign out", Icons.Filled.ExitToApp) {
        showExitAppDialog = true
    }

    if (showExitAppDialog) {
        AlertDialog(
            title = { Text("Sign Out") },
            text = { Text("Sign Out description") },
            dismissButton = {
                Button(onClick = { showExitAppDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    onSignOutClick()
                    showExitAppDialog = false
                }) {
                    Text(text = "Confirm")
                }
            },
            onDismissRequest = { showExitAppDialog = false }
        )
    }
}

@Composable
fun RemoveAccountCard(onRemoveAccountClick: () -> Unit) {
    var showRemoveAccDialog by remember { mutableStateOf(false) }

    AccountCenterCard("Delete Account", Icons.Filled.Delete) {
        showRemoveAccDialog = true
    }

    if (showRemoveAccDialog) {
        AlertDialog(
            title = { Text("Delete Account") },
            text = { Text("Delete account description") },
            dismissButton = {
                Button(onClick = { showRemoveAccDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    onRemoveAccountClick()
                    showRemoveAccDialog = false
                }) {
                    Text(text = "Delete account")
                }
            },
            onDismissRequest = { showRemoveAccDialog = false }
        )
    }
}