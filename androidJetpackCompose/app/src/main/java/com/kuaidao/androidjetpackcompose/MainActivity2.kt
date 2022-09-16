package com.kuaidao.androidjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ListItem
import androidx.compose.material3.Text
import androidx.compose.material.LocalContentAlpha
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kuaidao.androidjetpackcompose.ui.theme.AndroidJetpackComposeTheme
import kotlinx.coroutines.delay

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            AndroidJetpackComposeTheme {
                // testLocalAlpha()
                lazyColum()
            }
        }
    }
}


@Composable
fun testLocalAlpha() {
    Column {
        Text(
            "No content alpha applied - uses the default content alpha set by MaterialTheme - " + "87% alpha"
        )
        CompositionLocalProvider(LocalContentAlpha provides 1.00f) {
            Text("1.00f alpha applied - 100% alpha")
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            Text("High content alpha applied - 87% alpha")
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text("Medium content alpha applied - 60% alpha")
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
            Text("Disabled content alpha applied - 38% alpha")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun lazyColum() {
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(3000)
            refreshing = false
        }
    }

    val list = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6"
    )
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true }
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = list, itemContent = { item ->
                Text(
                    text = item.toString(), modifier = Modifier
                        .requiredHeight(50.dp)
                        .fillMaxWidth()
                )
            })
        }
    }

}
