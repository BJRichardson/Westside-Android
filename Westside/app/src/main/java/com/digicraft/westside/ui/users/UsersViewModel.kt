package com.digicraft.westside.ui.users

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside

open class UsersViewModel(val user: Westside.User, val context: Context?) : BaseObservable() {

    val name: String?
        get() = user.firstName


}