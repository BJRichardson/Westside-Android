package com.digicraft.westside.ui.drawer

import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside

class HeaderViewModel: BaseObservable() {
    var email: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    fun onUserRecieved(user: Westside.User) {
        //TODO Change to first name, last name
        //email = user.email
    }

    fun onError(throwable: Throwable) {
        email = ""
    }
}