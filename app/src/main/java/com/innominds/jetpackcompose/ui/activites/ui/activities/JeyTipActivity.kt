package com.innominds.jetpackcompose.ui.activites.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innominds.jetpackcompose.ui.activites.ui.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.utils.calcuateTotalTip
import com.innominds.jetpackcompose.utils.calculateTotalPerPerson
import com.innominds.jetpackcompose.widgets.RoundedIconButton

class JeyTipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Surface(
                    color = Color(250, 250, 250)
                ) {
                    ContentBody()
                }

            }
        }
    }
}

@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range: IntRange = 1..100,
    splitByState: MutableState<Int>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>,
    onValChange: (String) -> Unit = {}
) {

    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipPercent = (sliderPositionState.value * 100).toInt()



    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        TopHeader(totalPerPersonState.value)

        Surface(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
//            .fillMaxHeight(1.0f)
//            .height(300.dp)
            ,
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
//        color = colorResource(id = R.color.white),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                com.innominds.jetpackcompose.components.TextField(
                    label = "Enter Bill",
                    valueState = totalBillState,
                    hint = "Enter Bill Amount",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions {
                        if (!validState) {
                            return@KeyboardActions
                        }
                        onValChange(totalBillState.value.trim())
                        keyboardController?.hide()
                    }
                )

                if (validState) {
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.Start,

                        ) {
                        Text(
                            text = "Split",
                            modifier = Modifier
                                .weight(2f)
                                .align(
                                    alignment = Alignment.CenterVertically
                                )
                        )
                        Row(
                            modifier = Modifier
                                .weight(2f)
                                .padding(horizontal = 3.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            RoundedIconButton(
                                imageVector = Icons.Default.Remove,
                                onClick = {
                                    splitByState.value =
                                        if (splitByState.value > 1) splitByState.value.minus(1) else 1

                                    totalPerPersonState.value = calculateTotalPerPerson(
                                        totalBillState.value.toDouble(),
                                        splitByState.value,
                                        tipPercent
                                    )

                                },
                                tintColor = Color(33, 33, 33)
                            )
                            Text(
                                text = "${splitByState.value}",
                            )

                            RoundedIconButton(
                                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                                imageVector = Icons.Default.Add,
                                onClick = {
                                    if (splitByState.value < range.last)
                                        splitByState.value = splitByState.value.plus(1)

                                    totalPerPersonState.value = calculateTotalPerPerson(
                                        totalBillState.value.toDouble(),
                                        splitByState.value,
                                        tipPercent
                                    )

                                },
                                tintColor = Color(33, 33, 33)

                            )


                        }
                    }
                    //tIP rOw
                    Row {
                        Text(
                            text = "Tip",
                            modifier = Modifier
                                .weight(2f)
                                .padding(start = 10.dp, end = 10.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(200.dp))

                        Text(
                            text = "$ ${tipAmountState.value}",
                            modifier = Modifier
                                .weight(2f)
                                .align(alignment = Alignment.CenterVertically)
                        )
                    }
                    //Trip Percentage
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$tipPercent %",
                            modifier = Modifier
//                        .fillMaxWidth()
                                .align(alignment = Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Slider(
                            value = sliderPositionState.value,
                            onValueChange = { newValue ->
                                sliderPositionState.value = newValue
                                Log.d("Slide", "Bill Form Silder == $newValue")
                                if (totalBillState.value.toString().isEmpty()) return@Slider
                                tipAmountState.value =
                                    calcuateTotalTip(totalBillState.value.toDouble(), tipPercent)

                                totalPerPersonState.value =
                                    calculateTotalPerPerson(
                                        totalBillState.value.toDouble(),
                                        splitByState.value,
                                        tipPercent
                                    )
                            },
                            modifier = Modifier
                                .padding(start = 15.dp, end = 15.dp),
                            steps = 5,
                            onValueChangeFinished = {

                            }
                        )
                    }

                } else {
                    Box {}
                }


            }
        }
    }


}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

//@Preview()
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {

    Surface(
        color = Color(0xffE9D7F7),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(10.dp)))
//            .clip(shape = RoundedCornerShape(10.dp)),
//            .background(color = Color()),
    ) {
        Column(
            Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Total Per Person", style = TextStyle(
                    fontWeight = FontWeight.SemiBold, fontSize = 22.sp
                )
            )

            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "$$total", style = TextStyle(
                    fontWeight = FontWeight.ExtraBold, fontSize = 30.sp
                )
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ContentBody() {

    Column(
        modifier = Modifier.padding(12.dp)
    ) {

        val tipAmountState = remember {
            mutableDoubleStateOf(0.0)
        }

        val splitValue = remember {
            mutableStateOf(1)
        }

        val range = IntRange(start = 1, endInclusive = 100)

        val totalPerPersonState = remember {
            mutableDoubleStateOf(0.0)
        }

        BillForm(
            range = range,
            splitByState = splitValue,
            tipAmountState = tipAmountState,
            totalPerPersonState = totalPerPersonState

        ) {
        }
    }

}