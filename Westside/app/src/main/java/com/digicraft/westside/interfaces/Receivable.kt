package com.digicraft.westside.interfaces

import android.content.res.Resources
import android.util.Log
import com.digicraft.westside.R
import com.digicraft.westside.models.Westside
import retrofit2.HttpException

interface Receivable {
    interface Token : ErrorExtractable {
        fun onTokenReceived(token: Westside.Token) {
            //NOP
        }

        fun onTokenReceivedError(error: Throwable) {
            //NOP
        }

        override fun errorTextFromException(throwable: Throwable, resources: Resources): String {
            return if (throwable is HttpException) {
                when (throwable.code()) {
                    503 -> resources.getString(R.string.server_down)
                    else -> super.errorTextFromException(throwable, resources)
                }
            } else super.errorTextFromException(throwable, resources)
        }
    }

    interface User : ErrorExtractable {
        fun onUserReceived(user: Westside.User) {
            //NOP
        }

        fun onUserReceivedError(error: Throwable) {
            //NOP
        }
    }

    interface UserEvent : ErrorExtractable {
        fun onUserEventReceived(userEvent: Westside.UserEvent) {
            //NOP
        }

        fun onUserEventReceivedError(error: Throwable) {
            //NOP
        }
    }

    interface Event : ErrorExtractable {
        fun onEventReceived(posts: List<Westside.Event>) {
            //NOP
        }

        fun onEventReceivedError(error: Throwable) {
            Log.e("ERROR", error.message, null)
        }
    }

    interface Announcement : ErrorExtractable {
        fun onAnnouncementReceived(posts: List<Westside.Announcement>) {
            //NOP
        }

        fun onAnnouncementReceivedError(error: Throwable) {
            Log.e("ERROR", error.message, null)
        }
    }

    interface Prayer : ErrorExtractable {
        fun onPrayerReceived(posts: List<Westside.Prayer>) {
            //NOP
        }

        fun onPrayerReceivedError(error: Throwable) {
            Log.e("ERROR", error.message, null)
        }
    }

    interface UserGroup : ErrorExtractable {
        fun onUserGroupReceived(userGroup: Westside.UserGroup) {
            //NOP
        }

        fun onUserGroupReceivedError(error: Throwable) {
            //NOP
        }
    }

    interface Group : ErrorExtractable {
        fun onGroupsReceived(posts: List<Westside.Group>) {
            //NOP
        }

        fun onGroupReceivedError(error: Throwable) {
            Log.e("ERROR", error.message, null)
        }
    }

    interface Users : ErrorExtractable {
        fun onUsersReceived(posts: List<Westside.User>) {
            //NOP
        }

        fun onUsersReceivedError(error: Throwable) {
            Log.e("ERROR", error.message, null)
        }
    }
}
