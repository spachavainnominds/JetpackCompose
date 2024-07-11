package com.innominds.jetpackcompose.ui.screens.baseappbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innominds.jetpackcompose.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseAppBar(
    appBarTitle: String,
    navController: NavController?,
    contentView: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(appBarTitle = appBarTitle, navController = navController)
        },
        content = { BaseContent(contentView) }
    )
}

@Composable
fun BaseContent(contentView: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 56.dp),
    ) {
        contentView()
    }
}

@Composable
fun TopAppBar(appBarTitle: String, navController: NavController?) {
    Box {
        androidx.compose.material.TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            elevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    if (navController?.previousBackStackEntry != null) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = R.string.app_name),
                            modifier = Modifier
                                .size(30.dp)
                                .padding(5.dp)
                                .clickable(onClick = {
                                    Log.e("JetpackCompose", "Header Back Button Clicked")
                                    navController?.popBackStack()
                                }),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = appBarTitle,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp)
                    )
                }
            }
        }
    }
}