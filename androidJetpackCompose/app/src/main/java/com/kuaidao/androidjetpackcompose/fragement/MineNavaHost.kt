package com.kuaidao.androidjetpackcompose.fragement

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MineNavaHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(modifier = Modifier)
        }
        composable("profile") {
            ProfileScreen(modifier = Modifier)
        }
    }

}
