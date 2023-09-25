package com.iamtakuu.asap_timesheet_app.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.iamtakuu.asap_timesheet_app.navigation.ApplicationRouter
import com.iamtakuu.asap_timesheet_app.navigation.Screen
import com.iamtakuu.asap_timesheet_app.screens.LoginScreen
import com.iamtakuu.asap_timesheet_app.screens.SignUpScreen
import com.iamtakuu.asap_timesheet_app.screens.TaskCreationScreen
import com.iamtakuu.asap_timesheet_app.screens.TermsAndConditionsScreen

@Composable
fun TimeSheetApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = ApplicationRouter.currentScreen, label = "Screen Transition") { currentState ->
            when(currentState.value){
                is Screen.SignUpScreen ->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen ->{
                    TermsAndConditionsScreen()
                }
                is Screen.LoginScreen ->{
                    LoginScreen()
                }
                is Screen.TaskCreationScreen ->{
                    TaskCreationScreen()
                }
            }
        }
    }
}