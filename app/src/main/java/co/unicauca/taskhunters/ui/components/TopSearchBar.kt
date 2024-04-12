package co.unicauca.taskhunters.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.unicauca.taskhunters.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    onMenuClick: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    SearchBar(
        query = searchQuery, // Pass the state variable
        onQueryChange = { newQuery -> searchQuery = newQuery }, // Update on change
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(
                stringResource(R.string.placeholder_search),
                modifier = Modifier.padding(start = 5.dp)
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(end = 15.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

    }
}