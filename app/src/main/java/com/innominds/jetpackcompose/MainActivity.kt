package com.innominds.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innominds.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
fun GreetingPreview() {

    /*val fontFamily = remember {
        FontFamily(
            typeface = Typeface.createFromAsset(,"font/Regular.otf")
        )
    }*/

    JetpackComposeTheme {
//        Greeting("Android")
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
                    .wrapContentSize(),
                onClick = { /*TODO*/ }) {

                Text(text = stringResource(id = R.string.app_name),
                    fontSize = 16.sp,
                    fontFamily = appFontsFamily,
                    fontWeight = FontWeight.Bold,
                    )
            }

            TextField(
                textStyle = TextStyle(
                    fontFamily = appFontsFamily,
                    fontWeight = FontWeight.Bold,
                ),
                value = tfState.value,
                onValueChange = {
                    tfState.value = it
                },
                label = { Text(text = "Enter Label") },

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

        }

    }
}