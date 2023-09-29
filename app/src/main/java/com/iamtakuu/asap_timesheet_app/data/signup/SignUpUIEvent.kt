package com.iamtakuu.asap_timesheet_app.data.signup

sealed class SignUpUIEvent{

    data class EmailChanged(val email:String): SignUpUIEvent()
    data class PasswordChanged(val password: String) : SignUpUIEvent()
    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : SignUpUIEvent()
    //object RegisterButtonClicked : SignUpUIEvent()
}
