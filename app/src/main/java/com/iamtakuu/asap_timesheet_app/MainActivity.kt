package com.iamtakuu.asap_timesheet_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.iamtakuu.asap_timesheet_app.module.UserViewModel
import com.iamtakuu.asap_timesheet_app.navigation.graphs.RootNavigationGraph
import com.iamtakuu.asap_timesheet_app.ui.theme.ASAPTimesheetAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASAPTimesheetAppTheme {
               RootNavigationGraph(navController = rememberNavController())

            }
        }
    }
}

//@Composable
//@Preview
//fun PreviewApp(){
//    TimeSheetApp()
//}
