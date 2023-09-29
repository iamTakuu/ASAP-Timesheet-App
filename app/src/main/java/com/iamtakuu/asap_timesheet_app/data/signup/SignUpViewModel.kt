package com.iamtakuu.asap_timesheet_app.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtakuu.asap_timesheet_app.data.rules.Validator
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.navigation.ApplicationRouter
import com.iamtakuu.asap_timesheet_app.navigation.Screen
import com.iamtakuu.asap_timesheet_app.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel(){
    //For Logs
    private val TAG = SignUpViewModel::class.simpleName

    var signUpUIState = mutableStateOf(SignUpUIState())
    var signUpInProgress = mutableStateOf(false)
    //var alreadyRegistered = mutableStateOf(false)
    var allValidationsPassed = mutableStateOf(false)

    private val _uiState = MutableStateFlow<SupaResult<*>>(SupaResult.Loading)
    val uiState: StateFlow<SupaResult<*>>
        get() = _uiState

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }
    private fun validateDataWithRules() {

        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = signUpUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
                Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        signUpUIState.value = signUpUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status && privacyPolicyResult.status

    }
//Temporary data clas

    private fun registerUser(){
        viewModelScope.launch {
            repository.signUp(signUpUIState.value.email, signUpUIState.value.password).collectLatest { data ->
                _uiState.update { data }
            }
        }
    }
private fun observeSignUpState(onSignUpClick: () -> Unit) {
    viewModelScope.launch {
        uiState.collectLatest { data ->
            when(data){
                is SupaResult.Error -> {
                    signUpInProgress.value = false
                    if (data.message == "User already registered"){
                        ApplicationRouter.navigateTo(Screen.LoginScreen)
                    }
                    Log.e("SignUpVM", "Message ${data.message}")
                }
                is SupaResult.Loading -> {
                    signUpInProgress.value = true
                    Log.e("SignUpVM", "Loading...")
                }
                is SupaResult.Success -> {
                    Log.e("SignUpVM", "Epic ${data.data}")
                    //ApplicationRouter.navigateTo(Screen.TaskCreationScreen)
                    onSignUpClick()
                }
            }
        }

    }
}
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signUpUIState.value.toString())
    }

    fun attemptSignUp(onSignUpClick: () -> Unit) {
        validateDataWithRules()
        if (allValidationsPassed.value){
            registerUser()
            observeSignUpState(onSignUpClick)
        }
    }
}