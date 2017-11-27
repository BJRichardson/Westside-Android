package com.digicraft.westside.ui.ministries

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

open class MinistryViewModel(val group: Westside.Group, val serviceManager: AuthenticatedServiceManager, val context: Context) : BaseObservable() {

    val name: String?
        get() = group.name

    val chairperson: String?
        get() {
            return "Chairperson: " + group.chairperson
        }

    val description: String?
        get() = group.description

    val phone: String?
        get() {
            return "Phone: " + group.phone
        }

    val email: String?
        get() {
            return "Email: " + group.email
        }

    private var isMember: Boolean = false
        set(value) {
            field = value
            if (isMember) {
                ministryButtonText = context.getString(R.string.leave_ministry)
            } else {
                ministryButtonText = context.getString(R.string.join_ministry)
            }
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.ministryButtonText)
        }

    private var isRunningRequest: Boolean = false
        set(value) {
            field = value
            if (isRunningRequest) {
                progressVisibility = View.VISIBLE
                ministryButtonVisibility = View.GONE
            } else {
                progressVisibility = View.GONE
                ministryButtonVisibility = View.VISIBLE
            }
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.progressVisibility)
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.ministryButtonVisibility)
        }

    @Bindable
    var ministryButtonText: String = if (isMember()) context.getString(R.string.leave_ministry)  else context.getString(R.string.join_ministry)

    @Bindable
    var progressVisibility: Int = View.GONE

    @Bindable
    var ministryButtonVisibility: Int = View.VISIBLE

    fun onMinistryButtonClicked(view: View) {
        if (!serviceManager.isAuthenticated) {
            Toast.makeText(context, "Please Sign In", Toast.LENGTH_LONG).show()
            return
        }

        isRunningRequest = true
        if (isMember) {
            serviceManager.leaveMinistry(group.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io()).subscribe(this::onLeaveSuccess, this::onError)
        } else {
            serviceManager.joinMinistry(group.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io()).subscribe(this::onSuccess, this::onError)
        }
    }

    fun onSuccess(userGroup: Westside.UserGroup) {
        refreshUI()
    }

    fun onLeaveSuccess(response: ResponseBody) {
        refreshUI()
    }

    private fun refreshUI() {
        isMember = !isMember
        isRunningRequest = false
        notifyChange()
    }

    fun onError(error: Throwable) {
        isRunningRequest = false
        notifyChange()
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    fun isMember(): Boolean {
//        for (user in group.users) {
//            if (user.id == serviceManager.getCurrentUser()?.id) {
//                isMember = true
//                notifyChange()
//                return isMember
//            }
//        }
//        isMember = false
//        notifyChange()
        return isMember
    }
}
