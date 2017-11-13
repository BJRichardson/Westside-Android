package com.digicraft.westside.validators

import android.content.res.Resources
import com.digicraft.westside.R


class ConfirmPasswordValidator(private val passwordValidator: PasswordValidator, val resources: Resources): Validator() {
    override val errorMessage: String
        get() = resources.getString(R.string.confirm_password_validation_error)

    override fun isValid(textToValidate: String): Boolean {
        return textToValidate == passwordValidator.textView.text.toString()
    }
}
