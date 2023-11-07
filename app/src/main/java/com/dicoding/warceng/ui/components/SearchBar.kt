package com.dicoding.warceng.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.warceng.R
import com.dicoding.warceng.ui.theme.LightGray
import com.dicoding.warceng.ui.theme.SubmissionJetpackComposeTheme
import com.dicoding.warceng.ui.theme.coffeeColor
import com.dicoding.warceng.ui.theme.lightCoffee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = LightGray
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.search_title))
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .heightIn(min = 48.dp)
    ) {

    }
}

@Preview
@Composable
fun SearchBarPrev() {
    SubmissionJetpackComposeTheme {
        SearchBar(
            query = "",
            onQueryChange = {String}
        )
    }
}