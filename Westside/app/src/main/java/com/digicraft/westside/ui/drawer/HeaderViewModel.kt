package com.digicraft.westside.ui.drawer

import android.databinding.BaseObservable

class HeaderViewModel: BaseObservable() {
    var email: String = ""
        set(value) {
            field = value
            notifyChange()
        }
//    fun onUserRecieved(user: Fox.User) {
//        email = user.emailAddress
//    }
//
//    fun onError(throwable: Throwable) {
//        email = ""
//    }
}