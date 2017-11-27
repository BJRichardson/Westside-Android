package com.digicraft.westside.ui.drawer

import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside

class HeaderViewModel: BaseObservable() {
    var name: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    fun onUserRecieved(user: Westside.User) {
        //TODO Change to first name, last name
        name = "Hello " + user.firstName
    }

    fun onError(throwable: Throwable) {
        name = ""
    }

    fun onSignedOut() {
        name = ""
    }
}