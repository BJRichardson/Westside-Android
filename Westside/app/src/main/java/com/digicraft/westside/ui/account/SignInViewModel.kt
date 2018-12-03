package com.digicraft.westside.ui.account

import android.content.res.Resources
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.digicraft.westside.R
import com.digicraft.westside.interfaces.KeyboardDismissible
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.WestsideService
import com.digicraft.westside.validators.EmailAddressValidator
import com.digicraft.westside.validators.PasswordValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SignInViewModel(val service: WestsideService, val resources: Resources, private val activity: AppCompatActivity) : BaseObservable(), KeyboardDismissible, Receivable.Token {
    val emailValidator = EmailAddressValidator(resources)
    val passwordValidator = PasswordValidator(resources)

    var username: String = ""
    var password: String = ""

    private var isLoading: Boolean = false
        set(value) {
            field = value
            if (isLoading) {
                signInButtonText = ""
                progressBarVisibility = View.VISIBLE
            } else {
                signInButtonText = resources.getString(R.string.sign_in_caps)
                progressBarVisibility = View.GONE
            }
            notifyPropertyChanged(progressBarVisibility)
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.signInButtonText)
        }

    @Bindable
    var errorText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.errorText)
        }

    @Bindable
    var signInButtonText: String = resources.getString(R.string.sign_in_caps)

    @Bindable
    var progressBarVisibility: Int = View.GONE

    fun onSignInClicked() {
        isLoading = true
        service.signIn(username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(this::onTokenReceived, this::onTokenReceivedError)
    }

    override fun onTokenReceived(token: Westside.Token) {
        super.onTokenReceived(token)
        isLoading = false
        activity.finish()
    }

    override fun onTokenReceivedError(error: Throwable) {
        super.onTokenReceivedError(error)
        isLoading = false
        errorText = errorTextFromException(error, resources)
        notifyChange()
    }
}
