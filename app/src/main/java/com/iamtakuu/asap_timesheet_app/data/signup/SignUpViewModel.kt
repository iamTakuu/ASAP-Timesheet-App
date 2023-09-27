package com.iamtakuu.asap_timesheet_app.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtakuu.asap_timesheet_app.data.rules.Validator
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.navigation.ApplicationRouter
import com.iamtakuu.asap_timesheet_app.navigation.Screen
import com.iamtakuu.asap_timesheet_app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel(){
    //For Logs
    private val TAG = SignUpViewModel::class.simpleName

    var signUpUIState = mutableStateOf(SignUpUIState())
    var signUpInProgress = mutableStateOf(false)
    var allValidationsPassed = mutableStateOf(false)

    private val _uiState = MutableStateFlow<SupaResult<*>>(SupaResult.Loading)
    val uiState: StateFlow<SupaResult<*>>
        get() = _uiState

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                printState()

            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
            is SignUpUIEvent.RegisterButtonClicked -> {
                if (allValidationsPassed.value){
                    registerUser()
                    observeState()
                }
            }
        }
        validateDataWithRules()
    }
    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = signUpUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = signUpUIState.value.lastName
        )

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
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }
//Temporary data class
    @Serializable
    data class User(
        //val id: Int = 0,
        val email: String = "",
        val password: String = ""
    )
    @Serializable
    data class UserData(
        val first_name: String = "",
        val last_name: String = "",
    )



    private fun registerUser(){
        viewModelScope.launch {
            Log.e("", uiState.toString())
            val user = User(

                email = signUpUIState.value.email,
                password = signUpUIState.value.password
            )
            repository.registerUser(user).collectLatest { data ->
                _uiState.update { data }
            }
        }
    }
private fun observeState(){
    viewModelScope.launch {
        uiState.collectLatest { data ->
            when(data){
                is SupaResult.Error -> {
                    Log.e("SignUpVM", "Message ${data.message}")
                }
                is SupaResult.Loading -> {
                    signUpInProgress.value = true
                    Log.e("SignUpVM", "Loading...")
                }
                is SupaResult.Success -> {
                    Log.e("SignUpVM", "Epic ${data.data}")
                    ApplicationRouter.navigateTo(Screen.TaskCreationScreen)
                }
            }
        }

    }
}
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signUpUIState.value.toString())
    }
}