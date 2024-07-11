package com.innominds.jetpackcompose.ui.screens

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.innominds.jetpackcompose.theme.JetpackComposeTheme
import com.innominds.jetpackcompose.ui.screens.baseappbar.BaseAppBar

@Composable
fun LineCharts(navController: NavController) {
    // A surface container using the 'background' color from the theme
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        BaseAppBar(appBarTitle = "Line Charts", navController) {
            ContentData()
        }
    }
}


@Composable
fun ContentData() {
    val oxygenDataSet = createDataSetWithColor(
        datasetColor = android.graphics.Color.BLUE, label = "Heart Rate"
    )
    val lineDataOxygen = LineData(oxygenDataSet)
    LineChartCard(lineData = lineDataOxygen)
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    JetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        BaseAppBar(appBarTitle = "Line Charts", null) {
            ContentData()
        }
    }
}

@Composable
fun LineChartCard(modifier: Modifier = Modifier, lineData: LineData) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f) // (width:height) 2:1
            .padding(16.dp)
    ) {
        LineChartComponent(
            modifier = Modifier.fillMaxSize(), lineData = lineData
        )
    }
}

@Composable
fun LineChartComponent(modifier: Modifier = Modifier, lineData: LineData) {
    AndroidView(modifier = modifier, factory = { context ->
        LineChart(context).setupLineChart().apply {
            data = lineData
            xAxis.setDrawGridLines(false)
        }
    }, update = { view ->  /*Add animation here*/ })
}

fun createDataSetWithColor(
    datasetColor: Int = android.graphics.Color.GREEN, label: String = "No Label",
): LineDataSet {
    val entries = ArrayList<Entry>()
    entries.add(Entry(4f, 60f))
    entries.add(Entry(10f, 40f))
    entries.add(Entry(20f,  55f))

    return LineDataSet(entries, label).apply {
        color = datasetColor
        setDrawFilled(false)
        setDrawCircles(true)
        setCircleColors(Color.Blue.toArgb())
        mode = LineDataSet.Mode.CUBIC_BEZIER
        setDrawValues(false)
    }
}

fun LineChart.setupLineChart(): LineChart = this.apply {
    setTouchEnabled(true)
    isDragEnabled = true
    setScaleEnabled(true)
    setPinchZoom(true)
    description.isEnabled = false
    // set up x-axis
    xAxis.apply {
        position = XAxis.XAxisPosition.BOTTOM
        axisMinimum = 0f
        axisMaximum = 24f
    }
    // set up y-axis
    axisLeft.apply {
        // axisMinimum = -5f
        // axisMaximum = 5f
        setDrawGridLines(false)
    }
    axisRight.isEnabled = false
}
