package com.digicraft.westside.databinding

import android.databinding.BindingAdapter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.digicraft.westside.R
import com.digicraft.westside.validators.Validator
import com.squareup.picasso.Picasso

@BindingAdapter("validator")
fun setValidatorForTextView(textView: TextView, validator: Validator) {
    validator.textView = textView
    textView.addTextChangedListener(validator)
}

@BindingAdapter("textWatcher")
fun setTextWatcherForEditText(editText: EditText, formatter: TextWatcher) {
    editText.addTextChangedListener(formatter)
}

@BindingAdapter("imageUrl")
fun setImageViewResource(imageView: ImageView, resource: String) {
    Picasso
            .with(imageView.context)
            .load(resource)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)
}