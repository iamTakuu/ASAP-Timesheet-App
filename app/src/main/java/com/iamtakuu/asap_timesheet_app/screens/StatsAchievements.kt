package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.data.statistics.PieChart

@Composable
fun StatsAchievements(
    //viewModel: AchievementViewModel = hiltViewModel(),
   // navController: NavController
){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
        color = Color.White
    ){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                AchievementComponent(string = "First Task", color = Color.Blue)
                AchievementComponent(string = "5 Day Streak", color = Color.Green)
                StatisticsScreen()
            }
    }
}

// Component For Achievement
@Composable
fun AchievementComponent(string: String, color: Color){
    HeadingTextComponent(value = string)
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
    )
}


@Composable
fun StatisticsScreen() {
    LazyColumn(
        //modifier = Modifier.fillMaxSize(),
        content = {
            item {

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
    )
}


@Composable
@Preview
fun PrevAchievements(){
    StatsAchievements( )
}