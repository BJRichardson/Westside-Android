package com.digicraft.westside.databinding

import android.databinding.BindingAdapter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.digicraft.westside.validators.Validator

@BindingAdapter("validator")
fun setValidatorForTextView(textView: TextView, validator: Validator) {
    validator.textView = textView
    textView.addTextChangedListener(validator)
}

@BindingAdapter("textWatcher")
fun setTextWatcherForEditText(editText: EditText, formatter: TextWatcher) {
    editText.addTextChangedListener(formatter)
}
