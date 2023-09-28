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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.ButtonComponent
import com.iamtakuu.asap_timesheet_app.components.CheckboxComponent
import com.iamtakuu.asap_timesheet_app.components.ClickableLoginComponent
import com.iamtakuu.asap_timesheet_app.components.DividerTextComponent
import com.iamtakuu.asap_timesheet_app.components.GoogleSignComponent
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.components.MetaSignComponent
import com.iamtakuu.asap_timesheet_app.components.NormalTextComponent
import com.iamtakuu.asap_timesheet_app.components.PasswordFieldComponent
import com.iamtakuu.asap_timesheet_app.components.TextFieldComponent
import com.iamtakuu.asap_timesheet_app.data.signup.SignUpUIEvent
import com.iamtakuu.asap_timesheet_app.data.signup.SignUpViewModel
import com.iamtakuu.asap_timesheet_app.navigation.ApplicationRouter
import com.iamtakuu.asap_timesheet_app.navigation.Screen


@Composable
fun SignUpScreen(signupViewModel: SignUpViewModel = viewModel()) {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp),
            color = Color.White
        ){
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextSelected = {
                        signupViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.signUpUIState.value.emailError
                )
                PasswordFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.signUpUIState.value.passwordError
                )
                CheckboxComponent(
                    onTextSelected = {
                    ApplicationRouter.navigateTo(Screen.TermsAndConditionsScreen)
                },
                    onCheckedChange = {
                        signupViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))

                    }
                )
                Spacer(modifier = Modifier.height(200.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value)

                Spacer(modifier = Modifier.height(5.dp))

                DividerTextComponent()
                
                Column{
                    GoogleSignComponent()
                    Spacer(modifier = Modifier.height(10.dp))
                    MetaSignComponent()
                }

                Spacer(modifier = Modifier.height(5.dp))

                ClickableLoginComponent(tryingToLogin = true, onTextSelected = {
                    ApplicationRouter.navigateTo(Screen.LoginScreen)
                })

                if(signupViewModel.signUpInProgress.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()
}