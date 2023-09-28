package com.iamtakuu.asap_timesheet_app.data.rules

object Validator {

    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (fName.isNotEmpty() && fName.length >= 2)
        )
    }
    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (lName.isNotEmpty() && lName.length >= 2)
        )
    }
    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }
    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }

    fun validateStartDate(startDate: String):ValidationResult{
        return ValidationResult(
            (startDate.isNotEmpty())
        )
    }

    fun validateEndDate(endDate: String):ValidationResult{
        return ValidationResult(
            (endDate.isNotEmpty())
        )
    }

    fun validateCategory(category: String):ValidationResult{
        return ValidationResult(
            (category.isNotEmpty())
        )
    }

    fun validateMinTime(minTime: String): ValidationResult {
        return ValidationResult(
            validateTime(minTime)
        )
    }

    fun validateMaxTime(maxTime: String): ValidationResult {
        return ValidationResult(
            validateTime(maxTime)
        )
    }

    private fun validateTime(time: String): Boolean {
        return time.isNotEmpty() &&
                time.substring(0, 2).toIntOrNull() != null &&
                time.substring(2, 3) == ":" &&
                time.substring(3, 5).toIntOrNull() != null
    }

    // NB: "Icon", "Tags", and "Description" will not be validated as they are optional
}

data class ValidationResult(
    val status: Boolean = false
)