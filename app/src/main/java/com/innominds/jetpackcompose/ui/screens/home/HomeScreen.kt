package com.innominds.jetpackcompose.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.innominds.jetpackcompose.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.ui.models.Users
import com.innominds.jetpackcompose.ui.navigations.AppNavigationScreens
import com.innominds.jetpackcompose.ui.screens.baseappbar.BaseAppBar
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun HomeScreen(navController: NavController) {
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(navController = navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController?) {

    BaseAppBar(appBarTitle = "Users", navController) {
        CreateContent(navController = navController)
    }
}

@Preview(showSystemUi = true)
@Composable
fun Content() {
    MainScreen(null)
}

@Composable
fun CreateContent(navController: NavController?) {
    Log.d("JetpackCompose", "Inside Create Content Function..")
    Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surface
            )
        ) {
            val list = users()
            PortFolios(data = list, navController = navController)
        }
    }
}

@Composable
fun CreateProfile(modifier: Modifier, userLogo: String) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Gray),
        shadowElevation = 4.dp,
//        color = Color(245, 245, 245)//MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Log.e("JetpackCompose", "User Logo: $userLogo" )
        val painter = rememberImagePainter(
            data = userLogo,
        )

        Image(
            painter = painter,
            contentDescription = "User Logo",
            modifier = Modifier.size(150.dp),
        )
    }
}

@Composable
private fun UserDivider() {
    HorizontalDivider(
        modifier = Modifier
            .padding(top = 10.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
}


@Composable
private fun CreateInfo() {
    Column(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Miles P.",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Blue,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Android Compose Program",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            text = "spachava@innominds.com",
            fontSize = 18.sp,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun BtnPortfolio(btnClickState: MutableState<Boolean>) {
    Button(
        shape = RectangleShape,
//                    border = BorderStroke(width = 5.dp, ),
        onClick = {
            Log.d("JetpackCompose", "Portfolio button Click...")
            btnClickState.value = !btnClickState.value
        }
    ) {
        Text(
            text = "Portfolio",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun users(): ArrayList<Users> {
    val list = ArrayList<Users>()
    UsersList(list)
    return list
}


@Composable
private fun UsersList(list: ArrayList<Users>) {
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_02.jpg",
            userName = "Karthik Peddamallu",
            userRole = "Associate Manager",
            userEmail = "kpedamallu@innominds.com"
            )
    )

    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
            userName = "Srikanth Gajulla",
            userRole = "Principle Engineer",
            userEmail = "sgajula@innominds.com"
        )
    )
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_03.jpg",
            "Karthik Tiggura",
            "Quality Engineering",
            userEmail = "ktiyyagura@innominds.com"
        )
    )
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_10.jpg",
            "Phani Gajulla",
            "Associate Manager",
            userEmail = "pgajula@innominds.com"
        )
    )
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_12.jpg",
            "Mamatha Sheelam",
            "Principle Engineer",
            "msheelam@innominds.com"
        )
    )
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_11.jpg",
            "Purva Suresh Chaudhri",
            "Senior Engineer",
            "pchaudhari@innominds.com"
        )
    )
    list.add(
        Users(
            "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_09.jpg",
            "Sudhakar Pachava",
            "Senior Engineer",
            "spachava@innominds.com"
        )
    )
}

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun PortFolios(data: List<Users>, navController: NavController?) {
    Box {
        LazyColumn {
            items(data) { item: Users ->
                Card(
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(13.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    border = BorderStroke(8.dp, color = MaterialTheme.colorScheme.surface),
                    onClick = {
                        Log.d("JetpackCompose", "Card Clicked...")
                        // Encode to Base64
                        val encodedString: String = Base64.UrlSafe.encode(item.userLogo.toByteArray())
                        println("Encoded string: $encodedString")


                        Log.d("JetpackCompose", "User Name: $encodedString")
                        navController?.navigate(
                            route = AppNavigationScreens.DetailsScreen.name
                                    + "/${item.userName}" + "/${item.userRole}"+ "/${item.userEmail}"+ "/${encodedString}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        val modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                        CreateProfile(
                            modifier,
                            item.userLogo
                        )
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = item.userName,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = item.userRole,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                        }
                    }
//                UserDivider()
                }
            }
        }
    }

}