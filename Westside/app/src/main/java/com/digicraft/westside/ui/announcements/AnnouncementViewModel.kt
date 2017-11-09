package com.digicraft.westside.ui.announcements

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside
import java.text.SimpleDateFormat
import java.util.*

open class AnnouncementViewModel(val announcement: Westside.Announcement, val context: Context?) : BaseObservable() {
    val text: String?
        get() = announcement.announcement

    val poster: String?
        get() {
            return "Posted By: " + announcement.poster.firstName + " " + announcement.poster.lastName
        }

    val postedDate: String?
        get() {
            val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            return formatter.format(announcement.createdDate)
        }
}
