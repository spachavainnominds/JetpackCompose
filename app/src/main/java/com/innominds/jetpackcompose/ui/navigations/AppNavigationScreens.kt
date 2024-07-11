package com.innominds.jetpackcompose.ui.navigations

enum class AppNavigationScreens {
    HomeScreen,
    DetailsScreen,
    LineCharts;

    companion object {
        fun fromRoute(route: String): AppNavigationScreens = when (route) {
            "home" -> HomeScreen
            "details" -> DetailsScreen
            "lineschart" -> LineCharts
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}