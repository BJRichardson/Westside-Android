package com.digicraft.westside.interfaces

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


interface KeyboardDismissible {
    fun onClickOutsideInput(view: View) {
        val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
