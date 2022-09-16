package com.kuaidao.androidjetpackcompose.fragement

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuaidao.androidjetpackcompose.widget.NavWithBadge

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun homeScreen() {
    HomeScreen(modifier = Modifier)
}

@Composable
fun HomeScreen(modifier: Modifier) {
    Surface(
        contentColor = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
//        Column(modifier = Modifier) {
//            val username = remember { mutableStateOf("Hello") }
//            Topbar(modifier = Modifier,
//                title = username,
//                header = { logger("title") },
//                search = { logger("search") },
//                history = { logger("history") },
//                back = { logger("back") },
//                addFriends = { logger("addFriends") })
//        }

        BadgeDrawable()

    }
}

@Composable
fun Topbar(
    title: MutableState<String>? = null,
    back: () -> Unit,
    modifier: Modifier,
    header: () -> Unit,
    search: () -> Unit,
    history: () -> Unit,
    addFriends: () -> Unit
) {
    TopAppBar(title = {
        Row(
            modifier = Modifier.clickable(onClick = header),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(imageVector = Icons.Filled.AccountBox, contentDescription = null)
            Text(title?.value ?: "")
        }
    }, navigationIcon = {
        IconButton(onClick = back) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
    }, actions = {
        // RowScope here, so these icons will be placed horizontally
        IconButton(onClick = search) {
            Icon(Icons.Filled.Search, contentDescription = "Localized description")
        }
        IconButton(onClick = history) {
            Icon(Icons.Filled.Search, contentDescription = "Localized description")
        }
        IconButton(onClick = addFriends) {
            Icon(Icons.Default.Person, contentDescription = "Localized description")
        }
    })

}

@Composable
fun leftNavigator(
    currentRoute: String,
    navigatorHome: () -> Unit,
    navigatorInterests: () -> Unit,
    navigatorUserCenter: () -> Unit
) {
    Surface(modifier = Modifier) {
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
            NavWithBadge(
                icon = Icons.Filled.Home,
                action = navigatorHome,
                isSelected = currentRoute == ComposeDestinations.HOME_ROUTE
            )
            Spacer(modifier = Modifier.height(20.dp))
            NavWithBadge(
                icon = Icons.Filled.Create,
                action = navigatorInterests,
                isSelected = currentRoute == ComposeDestinations.INTERESTS_ROUTE
            )
            Spacer(modifier = Modifier.height(20.dp))
            NavWithBadge(
                icon = Icons.Filled.Face,
                badgeNumber = 10,
                action = navigatorUserCenter,
                isSelected = currentRoute == ComposeDestinations.USER_CENTER
            )
        }
    }

}


/**
 * Destinations used in the [JetnewsApp].
 */
object ComposeDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"
    const val USER_CENTER = "userCenter"
}


@Composable
fun HomeFrame(
    modifier: Modifier = Modifier,
    leftFrame: @Composable () -> Unit,
    rightFrame: @Composable ColumnScope.() -> Unit
) {
    Row {
        leftFrame()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
                .weight(weight = 1f, fill = true), verticalArrangement = Arrangement.Center
        ) {
            rightFrame()
        }
    }

}


@Preview(showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun BadgeDrawable() {
    val currentRoute = remember {
        mutableStateOf(ComposeDestinations.HOME_ROUTE)
    }
    HomeFrame(
        leftFrame = {
            leftNavigator(
                currentRoute = currentRoute.value,
                navigatorInterests = { currentRoute.value = ComposeDestinations.INTERESTS_ROUTE },
                navigatorHome = { currentRoute.value = ComposeDestinations.HOME_ROUTE },
                navigatorUserCenter = { currentRoute.value = ComposeDestinations.USER_CENTER })
        },
        rightFrame = {
            Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
                Text(text = currentRoute.value)
            }
        })
}


fun logger(value: String) {
    Log.d("Compose", value)
}