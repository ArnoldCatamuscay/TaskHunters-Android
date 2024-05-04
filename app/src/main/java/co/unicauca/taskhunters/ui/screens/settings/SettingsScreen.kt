package co.unicauca.taskhunters.ui.screens.settings


import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R


@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onRemoveAdsClick: () -> Unit,

    ) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedLanguage by remember{ mutableStateOf("false") }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box {
            Column {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back arrow"
                    )
                }
                OptionWithIconButton(
                    icon = Icons.Default.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.settingop1),
                    onClick = {showDialog=true}
                )
                if (showDialog) {
                    LanguageSelectionDialog(selectedLanguage = selectedLanguage,
                        onLanguageSelected = { language ->
                            selectedLanguage = language
                        },
                        onDismiss = { showDialog = false }
                    )
                }
                OptionWithIconButton(
                    icon = Icons.Default.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.settingop2),
                    onClick = onRemoveAdsClick
                )
                OptionWithIconButton(
                    icon = Icons.Default.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.settingop3),
                    onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ArnoldCatamuscay/TaskHunters-Android"))
                        launcher.launch(intent) }
                )

            }
        }
    }
}
@Composable
fun OptionWithIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(imageVector = icon, contentDescription = null)
        }
        Text(
            text = contentDescription,
            style = MaterialTheme.typography.titleLarge

            )
    }
}
@Composable
fun LanguageSelectionDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val languages = listOf(
        stringResource(R.string.Languageop1),
        stringResource(R.string.Languageop2),
        stringResource(R.string.Languageop3)
    )
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Column {
                languages.forEach { language ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = language == selectedLanguage,
                                onClick = { onLanguageSelected(language) }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = language == selectedLanguage,
                            onClick = { onLanguageSelected(language) },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = language)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {


                    onLanguageSelected(selectedLanguage)
                    onDismiss()
                }
            ) {
                Text("DE ACUERDO")
            }
        }
    )
}