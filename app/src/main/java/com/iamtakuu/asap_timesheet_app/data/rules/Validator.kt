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

    // Will implement proper validation later
    // v v v v v
    fun validateStartDate(startDate: String):ValidationResult{
        return ValidationResult(
            (startDate.isNotEmpty() && startDate.length >= 2)
        )
    }

    fun validateEndDate(endDate: String):ValidationResult{
        return ValidationResult(
            (endDate.isNotEmpty() && endDate.length >= 2)
        )
    }

    fun validateTime(time: Int):ValidationResult{
        return ValidationResult(
            (time > 0)
        )
    }

    fun validateCategory(category: String):ValidationResult{
        return ValidationResult(
            (category.isNotEmpty() && category.length >= 2)
        )
    }
    // ^ ^ ^ ^ ^

    // NB: "Icon", "Tags", and "Description" will not be validated as they are optional
}

data class ValidationResult(
    val status: Boolean = false
)