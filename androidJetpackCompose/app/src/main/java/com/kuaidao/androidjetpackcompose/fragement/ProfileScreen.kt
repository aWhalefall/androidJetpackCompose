package com.kuaidao.androidjetpackcompose.fragement

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ProfileScreen(modifier: Modifier) {
    Surface(
        contentColor = MaterialTheme.colors.onSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "个人中心",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
            )
        }


    }
}