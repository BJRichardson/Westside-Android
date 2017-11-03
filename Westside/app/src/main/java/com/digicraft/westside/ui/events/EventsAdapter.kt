package com.digicraft.westside.ui.events

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.digicraft.westside.databinding.ViewEventBinding
import com.digicraft.westside.models.Westside

class EventsAdapter(eventsList: List<Westside.Event>) : RecyclerView.Adapter<EventViewHolder>() {
    var events: List<Westside.Event> = eventsList

    override fun onBindViewHolder(holder: EventViewHolder?, position: Int) {
        holder?.binding?.vm = EventViewModel(events[position], holder?.itemView?.context)
        Log.d("FeedAdapter", "${events[position].title}")
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ViewEventBinding.inflate(layoutInflater, parent, false)
        val viewHolder = EventViewHolder(binding)
        viewHolder.itemView.setOnClickListener(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = events.size
}