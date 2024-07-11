package com.innominds.jetpackcompose.ui.activites

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innominds.jetpackcompose.R
import com.innominds.jetpackcompose.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.ui.models.Users

class ListComposeActivity : ComponentActivity() {

    private val TAG: String = "JetpackCompose"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting()
                    RenderCardView()
                }
            }
        }
    }
}

@Composable
fun RenderCardView() {
    val btnClickState = remember {
        mutableStateOf(value = false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

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

                CreateInfo()

                BtnPortfolio(btnClickState)

                if (btnClickState.value) {
                    Log.d("JetpackCompose", "Button Click == ${btnClickState.value}")
                    CreateContent()
                } else {
                    Log.d("JetpackCompose", "Button Click == ${btnClickState.value}")
                    Box { }
                }

            }
        }
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
private fun UserDivider() {
    HorizontalDivider(
        modifier = Modifier
            .padding(top = 10.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
}

@Composable
private fun CreateProfile(modifier: Modifier, userLogo: Int) {
    Surface(
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

@Composable
fun CreateContent() {
    Log.d("JetpackCompose", "Inside Create Content Function..")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
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

            PortFolios(data = list)
        }
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
  /*  list.add(
        Users(
            R.drawable.ic_user, "Karthik Peddamallu", "Associate Manager",
        )
    )

    list.add(
        Users(
            R.drawable.ic_user, "Srikanth Gajulla", "Principle Engineer"
        )
    )
    list.add(
        Users(
            R.drawable.ic_user, "Karthik Tiggura", "Senior Engineer"
        )
    )
    list.add(
        Users(
            R.drawable.ic_user, "Phani Gajulla", "Associate Manager"
        )
    )
    list.add(
        Users(
            R.drawable.ic_user, "Mamatha Sheelam", "Principle Engineer"
        )
    )
    list.add(
        Users(
            R.drawable.ic_user,
            "Purva Suresh Chaudhri",
            "Senior Engineer"
        )
    )
    list.add(
        Users(
            R.drawable.ic_user,
            "Sudhakar Pachava",
            "Senior Engineer",
        )
    )*/
}

@Composable
fun PortFolios(data: List<Users>) {
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
//                colors = CardColors(containerColor = MaterialTheme.colorScheme.surface)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)

                ) {

                    val modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)

                    CreateProfile(modifier, item.userLogo)
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = item.userName,
                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier
//                                .padding(start = 10.dp)
                        )
                        Text(
                            text = item.userRole,
                            fontWeight = FontWeight.Normal,
//                            modifier = Modifier
//                                .padding(start = 10.dp)
                        )
                    }
                }
//                UserDivider()
            }
        }
    }
}

@Composable
fun Greeting() {
    val tfState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
//            horizontalAlignment =  Alignment.Horizontal
    ) {
        Text(
            fontSize = 20.sp,
            fontFamily = appFontsFamily,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.app_name),
//                fontSize = 18,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,

                    )
                .wrapContentSize(
                    align = Alignment.TopStart
                )
        )

        Button(
//                shape = ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                )
                .wrapContentSize(), onClick = { /*TODO*/ }) {

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 16.sp,
                fontFamily = appFontsFamily,
                fontWeight = FontWeight.Bold,
            )
        }

        TextField(textStyle = TextStyle(
            fontFamily = appFontsFamily,
            fontWeight = FontWeight.Bold,
        ), value = tfState.value, onValueChange = {
            tfState.value = it
        }, label = { Text(text = "Enter Label") },

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,

                    )
                .wrapContentSize(
                    align = Alignment.TopStart
                )
        )

//            BottomNavigation()

    }
}

val appFontsFamily = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold)
)
//    Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JetpackUserProfile() {
    JetpackComposeTheme {
//        Greeting()
        RenderCardView()
    }
}

/*
@Composable
fun BottomNavigation() {

    val items = listOf(
        BottomNavItem.components,
        BottomNavItem.grid,
        BottomNavItem.caurosal,
        BottomNavItem.list
    )

    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem
) {
    NavigationBarItem(
        // Text that shows bellow the icon
        label = {
            Text(text = screen.label)
        },

        // The icon resource
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.label
            )
        },

        // Display if the icon it is select or not
        selected = true,

        // Always show the label bellow the icon or not
        alwaysShowLabel = true,

        // Click listener for the icon
        onClick = { *//*TODO*//*
 },

        // Control all the colors of the icon
        colors = NavigationBarItemDefaults.colors()
    )
}*/
