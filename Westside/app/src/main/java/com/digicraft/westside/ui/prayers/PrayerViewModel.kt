package com.digicraft.westside.ui.prayers

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside
import java.text.SimpleDateFormat
import java.util.*

open class PrayerViewModel(val prayer: Westside.Prayer, val context: Context?) : BaseObservable() {
    val text: String?
        get() = prayer.prayer

    val poster: String?
        get() {
            return "Posted By: " + prayer.poster.firstName + " " + prayer.poster.lastName
        }

    val postedDate: String?
        get() {
            val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            return formatter.format(prayer.createdDate)
        }
}