package com.kuaidao.androidjetpackcompose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuaidao.androidjetpackcompose.R


@Preview
@Composable
fun SearchWidget() {
    SearchBar(onSearch = {}, closeClick = {}, cancelListener = {})
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    cancelListener: () -> Unit,
    onSearch: () -> Unit,
    closeClick: () -> Unit
) {
    var textValue by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(44.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .background(
                    color = colorResource(
                        id = R.color.f2f5ff
                    ), shape = RoundedCornerShape(percent = 50)
                ).padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            TextField(label = { Text("input content") },
                value = textValue,
                onValueChange = { textValue = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        // do something here
                        onSearch()
                    }
                ))
            if (textValue.isNotBlank()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,

                    modifier = Modifier.clickable(onClick = closeClick)
                )
            }
            Text(
                text = stringResource(id = R.string.cancel),
                modifier = Modifier.clickable(onClick = closeClick)
            )
        }

    }


}

@Preview
@Composable
fun TextFieldSample() {
    Column {
        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("placeholder") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            },
            trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Localized description") }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Email") },
            placeholder = { Text("example@gmail.com") }
        )
    }
}