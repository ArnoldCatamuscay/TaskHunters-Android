package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R

@Composable
fun MySearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier.padding(start = 15.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(end = 15.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedContainerColor = Color.LightGray,
            focusedContainerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(30.dp),
        placeholder = {
            Text(
                stringResource(R.string.placeholder_search),
                modifier = Modifier.padding(start = 5.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun PreviewSearchBar(){
    MySearchBar()
}