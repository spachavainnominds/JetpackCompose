package com.innominds.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.innominds.jetpackcompose.ui.activites.JeyTipActivity

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.ComponentsScreen.name) {
//        composable(Navigation.ComponentsScreen.name) {
//            ComponentsActivity(navController)
//        }
        composable(Navigation.JeyTipScreen.name) {
            JeyTipActivity(navController)
        }
        composable(Navigation.ListComposeScreen.name) {
//            ListComposeActivity(navController)
        }
    }
}