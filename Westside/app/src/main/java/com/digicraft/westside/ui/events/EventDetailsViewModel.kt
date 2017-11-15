package com.digicraft.westside.ui.events

import android.content.Context
import android.databinding.BaseObservable
import android.view.View
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.models.Westside

class EventDetailsViewModel(event:Westside.Event, context: Context?): BaseObservable() {
    val coverUrl: String? = if (event.imageUrl != null) event.imageUrl else WestsideConfig.PLACEHOLDER_EVENT_URL
    val title: String = event.title
    val eventDescription: String = event.description ?: ""
    val addButtonVisibility = if (event.moreInformation != null) View.VISIBLE else View.GONE

    fun onAddEventClicked(view: View) {
        //Add Event Request
        //(this.context as EventDetailsActivity).addEvent(event.id)
    }
}
