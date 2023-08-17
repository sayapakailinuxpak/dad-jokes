package com.eldisprojects.dadjokes.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.eldisprojects.dadjokes.R

@Composable
fun SearchBar(searchText: String, valueChange: (String) -> Unit, focusManager: FocusManager, onSearch: () -> Unit = {}) {
    OutlinedTextField(
        value = searchText,
        onValueChange = valueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(12.dp),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null,
                colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(color = Color.White) else ColorFilter.tint(color = Color.Black)
            )
        },
        placeholder = {
            Text(text = "Search Jokes")
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            cursorColor = MaterialTheme.colors.onBackground,
            placeholderColor = if (isSystemInDarkTheme()) colorResource(id = R.color.gray) else colorResource(id = R.color.eerie_black),
            backgroundColor = if (isSystemInDarkTheme()) colorResource(id = R.color.raisin_black) else colorResource(id = R.color.anti_flash_white),
            unfocusedIndicatorColor = if (isSystemInDarkTheme()) colorResource(id = R.color.raisin_black) else colorResource(id = R.color.anti_flash_white)
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                onSearch()
            },
        )
    )
}