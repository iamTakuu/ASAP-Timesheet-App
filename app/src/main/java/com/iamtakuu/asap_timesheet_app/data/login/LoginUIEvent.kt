package com.iamtakuu.asap_timesheet_app.data.login

sealed class LoginUIEvent{
    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    //class LoginButtonClicked(onLoginSuccess: Unit) : LoginUIEvent()
}
