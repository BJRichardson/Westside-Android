package com.digicraft.westside.validators

import android.content.res.Resources
import com.digicraft.westside.R


class EmailAddressValidator(val resources: Resources): Validator() {
    override val errorMessage: String
        get() = resources.getString(R.string.email_validation_error)

    override fun isValid(textToValidate: String): Boolean {
        return textToValidate.contains("@")
    }
}
