package com.digicraft.westside.ui.events

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.R
import com.digicraft.westside.models.Westside
import java.text.SimpleDateFormat
import java.util.*

open class EventViewModel(val event: Westside.Event, val context: Context?) : BaseObservable() {
        val coverUrl: String?
            get() = ""

    val title: String?
        get() = event.title

    val ministry: String?
        get() = event.groups.first().name

    val description: String?
        get() = event.description

    val day: String?
        get() {
            val formatter = SimpleDateFormat("dd", Locale.US)
            return formatter.format(event.startTime)
        }

    val month: String?
        get() {
            val formatter = SimpleDateFormat("MMM", Locale.US)
            return formatter.format(event.startTime)
        }

    val time: String?
        get() {
            val formatter = SimpleDateFormat("hh:mm a", Locale.US)
            return formatter.format(event.startTime)
        }
}