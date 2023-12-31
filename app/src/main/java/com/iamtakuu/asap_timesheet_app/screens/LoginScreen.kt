@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)


package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.ButtonComponent
import com.iamtakuu.asap_timesheet_app.components.ClickableLoginComponent
import com.iamtakuu.asap_timesheet_app.components.DividerTextComponent
import com.iamtakuu.asap_timesheet_app.components.GoogleSignComponent
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.components.MetaSignComponent
import com.iamtakuu.asap_timesheet_app.components.NormalTextComponent
import com.iamtakuu.asap_timesheet_app.components.PasswordFieldComponent
import com.iamtakuu.asap_timesheet_app.components.TextFieldComponent
import com.iamtakuu.asap_timesheet_app.components.UnderLinedTextComponent
import com.iamtakuu.asap_timesheet_app.data.login.LoginUIEvent
import com.iamtakuu.asap_timesheet_app.data.login.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(20.dp))

            if (loginViewModel.loginInError.value){
                UnderLinedTextComponent(value = stringResource(id = R.string.invalid_login))
            }

            Spacer(modifier = Modifier.height(180.dp))

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {
                    loginViewModel.attemptLogin(onLoginClick)
                    //loginViewModel.loginInError.value != loginViewModel.loginInError.value
                },
                isEnabled = loginViewModel.allValidationsPassed.value)

            DividerTextComponent()

            Column{
                GoogleSignComponent()
                Spacer(modifier = Modifier.height(10.dp))
                MetaSignComponent()
            }
            Spacer(modifier = Modifier.height(5.dp))

            ClickableLoginComponent(tryingToLogin = false, onTextSelected = {
                onSignUpClick()
            })

            Spacer(modifier = Modifier.height(20.dp))

            if(loginViewModel.loginInProgress.value) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
//            SystemBackButtonHandler {
//        ApplicationRouter.navigateTo(Screen.SignUpScreen)
//    }
}

//@Preview
//@Composable
//fun PreviewLogin(){
//    LoginScreen {
//        navController.popBackStack()
//        navController.navigate(com.iamtakuu.asap_timesheet_app.navigation.graphs.Graph.HOME)
//    }
//}