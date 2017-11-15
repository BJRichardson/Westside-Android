package com.digicraft.westside.ui.events

import android.content.Context
import android.view.View
import com.digicraft.westside.models.Westside

class EventDetailsViewModel(event:Westside.Event, context: Context?, isAuthed: Boolean): EventViewModel(event, context) {
    val eventDescription: String = event.description ?: ""
    val addButtonVisibility = if (isAuthed) View.VISIBLE else View.GONE

    fun onAddEventClicked(view: View) {
        //Add Event Request
    }
}
