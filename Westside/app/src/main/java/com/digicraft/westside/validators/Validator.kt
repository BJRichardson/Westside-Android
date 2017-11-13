package com.digicraft.westside.validators

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView


abstract class Validator: TextWatcher {

    lateinit var textView: TextView
    abstract val errorMessage: String
    abstract fun isValid(textToValidate: String): Boolean

    override fun afterTextChanged(editable: Editable?) {
        val currentText = textView.text.toString()
        if (!isValid(currentText)) {
            Log.d("Validator", "Setting Error")
            textView.error = errorMessage
        }
    }

    override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
        //NOP
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        //NOP
    }
}