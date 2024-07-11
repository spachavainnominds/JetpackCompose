package com.innominds.jetpackcompose.ui.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.innominds.jetpackcompose.R
import com.innominds.jetpackcompose.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.ui.navigations.AppNavigationScreens
import com.innominds.jetpackcompose.ui.screens.baseappbar.BaseAppBar


@Composable
fun DetailsScreen(
    navController: NavController,
    name: String?,
    role: String?,
    email: String?,
) {
    // A surface container using the 'background' color from the theme
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                navController = navController,
                name,
                role,
                email
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController?,
               name: String?,
               role: String?,
               email: String?) {
    BaseAppBar(appBarTitle = "User Details", navController = navController) {
        RenderCardView(navController, name, role, email)
    }
}

@Composable
fun TopAppBar() {
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "User Details",
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

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    JetpackComposeTheme {
        MainScreen(navController = null,  "", role = "role", email = "")
    }
}

@Composable
fun RenderCardView(navController: NavController?,
                   name: String?,
                   role: String?,
                   email: String?) {
    val btnClickState = remember {
        mutableStateOf(value = false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val modifier = Modifier
                    .size(150.dp)
                    .padding(5.dp)

                CreateProfile(modifier, R.drawable.ic_user)

                UserDivider()

                CreateInfo(name , role,  email)

                BtnPortfolio(navController)

                if (btnClickState.value) {
                    Log.d("JetpackCompose", "Button Click == ${btnClickState.value}")
                    com.innominds.jetpackcompose.ui.activites.CreateContent()
                } else {
                    Log.d("JetpackCompose", "Button Click == ${btnClickState.value}")
                    Box {

                    }
                }

            }
        }
    }
}

@Composable
fun BtnPortfolio(navController: NavController?) {
    Button(
        shape = RoundedCornerShape(5.dp),
        onClick = {
            Log.d("JetpackCompose", "Portfolio button Click...")
//            btnClickState.value = !btnClickState.value
            navController?.navigate(AppNavigationScreens.LineCharts.name)
        }
    ) {
        Text(
            text = "Line Charts",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun CreateInfo(name: String?, role: String?, email: String?) {
    Column(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = name!!,
            fontSize = 24.sp,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = Color.Blue,
            textAlign = TextAlign.Center
        )

       Text(
            text = role!!,
            fontSize = 20.sp,
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            text = email!!,
            fontSize = 18.sp,
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UserDivider() {
    HorizontalDivider(
        modifier = Modifier
            .padding(top = 10.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
}

@Composable
fun CreateProfile(modifier: Modifier, userLogo: Int) {
    androidx.compose.material3.Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(4.dp, Color.Gray),
        shadowElevation = 4.dp,
        color = Color(245, 245, 245)//MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = userLogo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(130.dp),
            contentScale = ContentScale.Crop
        )
    }
}