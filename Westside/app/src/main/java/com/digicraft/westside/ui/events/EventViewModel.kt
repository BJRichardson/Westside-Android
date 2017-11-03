package com.digicraft.westside.ui.events

import android.content.Context
import android.databinding.BaseObservable
import com.digicraft.westside.models.Westside

open class EventViewModel(val event: Westside.Event, val context: Context?) : BaseObservable() {
        val coverUrl: String?
            get() = ""

    val name: String?
        get() = "This is a Sample"

    val date: String?
        get() = "Nov 3rd"

//    val coverUrl: String?
//        get() = event.postUser.imageUrl
//
//    val name: SpannableString
//        get() {
//            val spannableString = SpannableString(event.postText.toUpperCase())
//            spannableString.setSpan(BackgroundColorSpan(Color.BLACK), 0, event.postText.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
//            spannableString.setSpan(ForegroundColorSpan(Color.WHITE), 0, event.postText.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
//            return spannableString
//        }
//
//    val date: SpannableString
//        get() {
//            val spannableString = SpannableString(event.createdDate)
//            spannableString.setSpan(BackgroundColorSpan(Color.BLACK), 0, event.createdDate.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
//            spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)), 0, event.createdDate.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
//            return spannableString
//        }
}