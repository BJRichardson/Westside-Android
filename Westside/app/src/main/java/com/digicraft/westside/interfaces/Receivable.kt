package com.digicraft.westside.interfaces

import android.util.Log
import com.digicraft.westside.models.Westside

interface Receivable {
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
}
