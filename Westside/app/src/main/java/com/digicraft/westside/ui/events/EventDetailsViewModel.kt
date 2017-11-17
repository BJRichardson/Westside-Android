package com.digicraft.westside.ui.events

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import android.widget.Toast
import com.digicraft.westside.R
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.models.Westside
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class EventDetailsViewModel(val serviceManager: AuthenticatedServiceManager, val event:Westside.Event, val context: Context): BaseObservable() {
    val coverUrl: String? = if (event.imageUrl != null) event.imageUrl else WestsideConfig.PLACEHOLDER_EVENT_URL
    val title: String = event.title
    val eventDescription: String = event.description ?: ""

    private var isAttending: Boolean = false
        set(value) {
            field = value
            if (isAttending) {
                eventButtonText = context.getString(R.string.leave_event)
                eventAttendees = getAttendees()
            } else {
                eventButtonText = context.getString(R.string.join_event)
                eventAttendees = getAttendees()
            }
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.eventButtonText)
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.eventAttendees)
        }

    private var isRunningRequest: Boolean = false
        set(value) {
            field = value
            if (isRunningRequest) {
                progressBarVisibility = View.VISIBLE
                eventButtonVisibility = View.GONE
            } else {
                progressBarVisibility = View.GONE
                eventButtonVisibility = View.VISIBLE
            }
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.progressBarVisibility)
            notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.eventButtonVisibility)
        }

    @Bindable
    var eventButtonText: String = if (isAttending()) context.getString(R.string.leave_event)  else context.getString(R.string.join_event)

    @Bindable
    var progressBarVisibility: Int = View.GONE

    @Bindable
    var eventButtonVisibility: Int = View.VISIBLE

    @Bindable
    var eventAttendees: String = getAttendees()

    fun onEventButtonClicked(view: View) {
        if (!serviceManager.isAuthenticated) {
            Toast.makeText(context, "Please Sign In", Toast.LENGTH_LONG).show()
            return
        }

        isRunningRequest = true
        if (isAttending) {
            serviceManager.leaveEvent(event.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io()).subscribe(this::onLeaveSuccess, this::onError)
        } else {
            serviceManager.joinEvent(event.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io()).subscribe(this::onSuccess, this::onError)
        }
    }

    fun onSuccess(userEvent: Westside.UserEvent) {
        refreshUI()
    }

    fun onLeaveSuccess(response: ResponseBody) {
        refreshUI()
    }

    private fun refreshUI() {
        isAttending = !isAttending
        isRunningRequest = false
        notifyChange()
    }

    fun onError(error: Throwable) {
        isRunningRequest = false
        notifyChange()
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    fun isAttending(): Boolean {
        for (user in event.users) {
            if (user.id == serviceManager.getCurrentUser()?.id) {
                isAttending = true
                notifyChange()
                return isAttending
            }
        }
        isAttending = false
        notifyChange()
        return isAttending
    }

    fun getAttendees(): String {
        var attendees = "Attendees: "
        var hasBeenAdded = false

        for (user in event.users) {
            if (user.id != serviceManager.getCurrentUser()?.id) {
                attendees = """$attendees ${user.firstName} ${user.lastName}, """
            } else if (isAttending) {
                hasBeenAdded = true
                attendees = """$attendees ${user.firstName} ${user.lastName}, """
            }
        }

        if (!hasBeenAdded && isAttending) {
            attendees = """$attendees ${serviceManager.getCurrentUser()?.firstName} ${serviceManager.getCurrentUser()?.lastName}, """
        }

        return attendees.dropLast(2)
    }
}
