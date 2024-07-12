package com.innominds.jetpackcompose.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.innominds.jetpackcompose.ui.screens.LineCharts
import com.innominds.jetpackcompose.ui.screens.details.DetailsScreen
import com.innominds.jetpackcompose.ui.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavigationScreens.HomeScreen.name
    ) {
        composable(AppNavigationScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(AppNavigationScreens.DetailsScreen.name + "/{name}" + "/{role}" + "/{email}" + "/{logo}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                },
                navArgument(name = "role") {
                    type = NavType.StringType
                },
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "logo") {
                    type = NavType.StringType
                }
            )
        ) {  backStackEntry ->
            DetailsScreen(navController = navController,
                backStackEntry.arguments?.getString("name"),
                backStackEntry.arguments?.getString("role"),
                backStackEntry.arguments?.getString("email"),
                backStackEntry.arguments?.getString("logo")
            )
        }

        composable(AppNavigationScreens.LineCharts.name) {
            LineCharts(navController = navController)
        }
    }
}