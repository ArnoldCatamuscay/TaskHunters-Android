package co.unicauca.taskhunters.ui.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    @StringRes placeholder: Int,
    value: String,
    onNewValue: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            value = value,
            visualTransformation = visualTransformation,
            onValueChange = { onNewValue(it) },
            placeholder = { Text(text = stringResource(id = placeholder)) },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = { onClearClick() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear field"
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
    }
}
