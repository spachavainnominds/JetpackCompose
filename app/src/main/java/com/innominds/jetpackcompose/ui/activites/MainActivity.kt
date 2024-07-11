@file:OptIn(ExperimentalMaterial3Api::class)

package com.innominds.jetpackcompose.ui.activites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.innominds.jetpackcompose.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.ui.navigations.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                AppNavigation()
            }
        }
    }
}




