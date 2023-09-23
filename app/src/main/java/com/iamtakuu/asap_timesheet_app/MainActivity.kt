package com.iamtakuu.asap_timesheet_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iamtakuu.asap_timesheet_app.app.TimeSheetApp
import com.iamtakuu.asap_timesheet_app.ui.theme.ASAPTimesheetAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASAPTimesheetAppTheme {
               TimeSheetApp()
            }
        }
    }
}

@Composable
@Preview
fun PreviewApp(){
    TimeSheetApp()
}
