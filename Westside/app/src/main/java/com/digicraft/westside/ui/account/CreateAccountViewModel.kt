package com.digicraft.westside.ui.account

import android.content.res.Resources
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.digicraft.westside.R
import com.digicraft.westside.interfaces.KeyboardDismissible
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.validators.ConfirmPasswordValidator
import com.digicraft.westside.validators.EmailAddressValidator
import com.digicraft.westside.validators.PasswordValidator
import com.digicraft.westside.managers.WestsideServiceManager

class CreateAccountViewModel(val resources: Resources, val service: WestsideServiceManager, private val activity: AppCompatActivity) : BaseObservable(), KeyboardDismissible, Receivable.Token {
    val emailValidator = EmailAddressValidator(resources)
    val passwordValidator = PasswordValidator(resources)
    val confirmPasswordValidator = ConfirmPasswordValidator(passwordValidator, resources)
    val phoneFormatter = PhoneNumberFormattingTextWatcher()

    var username: String = ""
    var password: String = ""
    var phoneNumber: String = ""
    var firstName: String = ""
    var lastName: String = ""
    val sanitizedPhoneNumber: String
        get() {
            return phoneNumber
                    .replace("(", "")
                    .replace(")", "")
                    .replace(" ", "")
                    .replace("-", "")
        }
    var isBroadwaySubscriber = false

    private var isLoading: Boolean = false
        set(value) {
            field = value
            if (isLoading) {
                createAccountButtonText = ""
                progressBarVisibility = View.VISIBLE
            } else {
                createAccountButtonText = resources.getString(R.string.sign_in_caps)
                progressBarVisibility = View.GONE
            }
            notifyPropertyChanged(BR.progressBarVisibility)
            notifyPropertyChanged(BR.createAccountButtonText)
        }

    @Bindable
    var errorText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorText)
        }

    @Bindable
    var createAccountButtonText: String = resources.getString(R.string.sign_in_caps)

    @Bindable
    var progressBarVisibility: Int = View.GONE

    fun onRegisterClicked() {
        isLoading = true
        service.register(Westside.New.User(username, password, firstName, lastName, sanitizedPhoneNumber))
                .subscribe(this::onTokenReceived, this::onTokenReceivedError)
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

