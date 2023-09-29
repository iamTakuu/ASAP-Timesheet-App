package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.data.statistics.PieChart

@Composable
fun StatisticsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Surface(
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Column {
                        HeadingTextComponent(value = stringResource(id = R.string.statistics))

                        Spacer(modifier = Modifier.height(50.dp))

                        // Preview with sample data
                        PieChart(
                            //Data used example "Name of Task" and "Value" such as time
                            //Value is auto added up and pie chart reflects percentage accordingly
                            data = mapOf(
                                Pair("Gym", 100),
                                Pair("Touch Grass", 120),
                                Pair("Video Games", 110),
                                Pair("Stream", 170),
                                Pair("Assignment", 120),
                            )
                        )
                    }
                }
            }
        }
    )
}



@Preview(showBackground = true, name = "Text Preview")
@Composable
fun StatsPreview() {
StatisticsScreen()
}