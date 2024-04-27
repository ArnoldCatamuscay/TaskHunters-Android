package co.unicauca.taskhunters.ui.screens.account_center

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.unicauca.taskhunters.R
import co.unicauca.taskhunters.model.User

@Composable
fun AccountCenterScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: AccountCenterViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState(initial = User())

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        if (!user.isAnonymous) {
            DisplayNameCard(user.displayName) { viewModel.onUpdateDisplayNameClick(it) }
            AccountCenterCard(user.email, Icons.Filled.Email){}
        }
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
fun DisplayNameCard(displayName: String, onUpdateDisplayNameClick: (String) -> Unit) {
    var showDisplayNameDialog by remember { mutableStateOf(false) }
    var newDisplayName by remember { mutableStateOf(displayName) }

    val cardTitle = displayName.ifBlank { stringResource(R.string.profile_name) }

    AccountCenterCard(cardTitle, Icons.Filled.Edit) {
        newDisplayName = displayName
        showDisplayNameDialog = true
    }

    if (showDisplayNameDialog) {
        AlertDialog(
            title = { Text(stringResource(R.string.profile_name)) },
            text = {
                Column {
                    TextField(
                        value = newDisplayName,
                        onValueChange = { newDisplayName = it }
                    )
                }
            },
            dismissButton = {
                Button(onClick = { showDisplayNameDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            confirmButton = {
                Button(onClick = {
                    onUpdateDisplayNameClick(newDisplayName)
                    showDisplayNameDialog = false
                }) {
                    Text(text = stringResource(id = R.string.update))
                }
            },
            onDismissRequest = { showDisplayNameDialog = false }
        )
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

    AccountCenterCard(stringResource(R.string.sign_out), Icons.Filled.ExitToApp) {
        showExitAppDialog = true
    }

    if (showExitAppDialog) {
        AlertDialog(
            title = { Text(stringResource(R.string.sign_out)) },
            text = { Text(stringResource(R.string.sign_out_description)) },
            dismissButton = {
                Button(onClick = { showExitAppDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            confirmButton = {
                Button(onClick = {
                    onSignOutClick()
                    showExitAppDialog = false
                }) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            onDismissRequest = { showExitAppDialog = false }
        )
    }
}

@Composable
fun RemoveAccountCard(onRemoveAccountClick: () -> Unit) {
    var showRemoveAccDialog by remember { mutableStateOf(false) }

    AccountCenterCard(stringResource(R.string.delete_account), Icons.Filled.Delete) {
        showRemoveAccDialog = true
    }

    if (showRemoveAccDialog) {
        AlertDialog(
            title = { Text(stringResource(R.string.delete_account)) },
            text = { Text(stringResource(R.string.delete_account_description)) },
            dismissButton = {
                Button(onClick = { showRemoveAccDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            confirmButton = {
                Button(onClick = {
                    onRemoveAccountClick()
                    showRemoveAccDialog = false
                }) {
                    Text(text = stringResource(R.string.delete_account))
                }
            },
            onDismissRequest = { showRemoveAccDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardAccountCenterScreen() {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                String.format(
                    stringResource(R.string.profile_email),
                    "foo@gmail.com"
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Filled.Email, contentDescription = "Icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardAccountCenterScreen2() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = String.format(stringResource(R.string.profile_email), "foo@gmail.com")
        )
    }
}