package com.iamtakuu.asap_timesheet_app.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtakuu.asap_timesheet_app.data.rules.Validator
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.repository.impl.AuthRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepositoryImp
): ViewModel(){
    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)
    var loginInError = mutableStateOf(false)


    private val _uiState = MutableStateFlow<SupaResult<*>>(SupaResult.Loading)
    val uiState: StateFlow<SupaResult<*>>
        get() = _uiState

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            else -> {}
        }
        validateLoginUIDataWithRules()
    }

    fun attemptLogin(onLoginClick: () -> Unit){
        validateLoginUIDataWithRules()
        if (allValidationsPassed.value){
            login()
            observeLoginState(onLoginClick)
        }
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        loginInProgress.value = true
        viewModelScope.launch {
            repository.signIn(loginUIState.value.email, loginUIState.value.password).collectLatest { data ->
                _uiState.update { data }
            }
        }
    }

    private fun observeLoginState(onLoginClick: () -> Unit) {
        viewModelScope.launch {
            uiState.collectLatest { data ->
                when(data){
                    is SupaResult.Error -> {
                        loginInProgress.value = false
                        loginInError.value = true
                        Log.e("SignUpVM", "Message ${data.message}")
                    }
                    is SupaResult.Loading -> {
                        loginInProgress.value = true
                        loginInError.value = false
                        Log.e("SignUpVM", "Loading...")
                    }
                    is SupaResult.Success -> {
                        Log.e("SignUpVM", "Epic ${data.data}")
                        onLoginClick()
                    }

                    else -> {}
                }
            }

        }
    }
}