package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.DateTimePickerComponent
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.components.NormalTextComponent
import com.iamtakuu.asap_timesheet_app.data.statistics.PieChart
import com.iamtakuu.asap_timesheet_app.ui.theme.Primary
import com.iamtakuu.asap_timesheet_app.ui.theme.Purple80
import com.iamtakuu.asap_timesheet_app.ui.theme.Secondary

@Composable
private fun StatisticsShow(modifier: Modifier = Modifier) {
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

                        Spacer(modifier = Modifier.height(10.dp))

                        // Start Date
                        DateTimePickerComponent(labelValue = stringResource(id = R.string.start_date))

                        Spacer(modifier = Modifier.height(10.dp))

                        // End Date
                        DateTimePickerComponent(labelValue = stringResource(id = R.string.end_date))

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
StatisticsShow()
}