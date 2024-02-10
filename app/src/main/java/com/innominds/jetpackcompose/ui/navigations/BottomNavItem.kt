package com.innominds.jetpackcompose.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.innominds.jetpackcompose.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {

    data object components : BottomNavItem("components", R.drawable.ic_bottom_nav_gradient, "Components")
    data object grid : BottomNavItem("grid", R.drawable.ic_bottom_nav_grid_view, "Grid")
    data object caurosal : BottomNavItem("caurosal", R.drawable.ic_bottom_nav_carousal, "Caurosal")
    data object list : BottomNavItem("list", R.drawable.ic_bottom_nav_list, "List")
}