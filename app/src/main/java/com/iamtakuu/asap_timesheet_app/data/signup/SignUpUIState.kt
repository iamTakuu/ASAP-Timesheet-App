package com.iamtakuu.asap_timesheet_app.data.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpUIState(
    var email  :String = "",
    var password  :String = "",
    var privacyPolicyAccepted :Boolean = false,

    var emailError :Boolean = false,
    var passwordError : Boolean = false,
    var privacyPolicyError:Boolean = false

)
