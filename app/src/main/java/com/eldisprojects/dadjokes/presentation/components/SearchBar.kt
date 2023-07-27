package com.eldisprojects.dadjokes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.eldisprojects.dadjokes.R

@Composable
fun SearchBar(searchText: String, valueChange: (String) -> Unit, focusManager: FocusManager) {
    OutlinedTextField(
        value = searchText,
        onValueChange = valueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(12.dp),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null
            )
        },
        placeholder = {
            Text(text = "Search Jokes")
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.onBackground,
            placeholderColor = colorResource(id = R.color.eerie_black),
            backgroundColor = colorResource(id = R.color.anti_flash_white),
            unfocusedIndicatorColor = colorResource(id = R.color.anti_flash_white)
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            },
        )
    )
}