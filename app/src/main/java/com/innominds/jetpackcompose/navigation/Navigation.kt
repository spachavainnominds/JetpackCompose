package com.innominds.jetpackcompose.navigation


enum class Navigation {
    ComponentsScreen, JeyTipScreen, ListComposeScreen;

    companion object {
        fun fromRoute(route: String): Navigation = when(route.substringBefore("/")) {
            "components" -> ComponentsScreen
            "jetTip" -> JeyTipScreen
            "listCompose" -> ListComposeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}
