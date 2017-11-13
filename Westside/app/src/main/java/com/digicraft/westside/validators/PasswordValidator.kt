package com.digicraft.westside.validators

import android.content.res.Resources
import com.digicraft.westside.R


class PasswordValidator(val resources: Resources) : Validator() {
    override val errorMessage: String
        get() = resources.getString(R.string.password_validation_string)

    override fun isValid(textToValidate: String): Boolean {
        return textToValidate.length > 5
    }
}
