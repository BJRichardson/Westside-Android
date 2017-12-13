package com.digicraft.westside.ui.users

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import android.widget.Toast
import com.digicraft.westside.R
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.models.Westside
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

open class UserViewModel(val user: Westside.User, val serviceManager: AuthenticatedServiceManager, val context: Context) : BaseObservable() {

    val name: String?
        get() {
            return user.firstName + " " + user.lastName
        }


    val phone: String?
        get() {
            return "Phone: " + user.phone
        }

    val email: String?
        get() {
            return "Email: " + user.email
        }

}