package com.digicraft.westside.ui.announcements

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.digicraft.westside.databinding.ViewAnnouncementBinding
import com.digicraft.westside.models.Westside

class AnnouncementsAdapter(announcementsList: List<Westside.Announcement>) : RecyclerView.Adapter<AnnouncementViewHolder>() {
    var announncements: List<Westside.Announcement> = announcementsList

    override fun onBindViewHolder(holder: AnnouncementViewHolder?, position: Int) {
        holder?.binding?.vm = AnnouncementViewModel(announncements[position], holder?.itemView?.context)
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnnouncementViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ViewAnnouncementBinding.inflate(layoutInflater, parent, false)
        val viewHolder = AnnouncementViewHolder(binding)
        viewHolder.itemView.setOnClickListener(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = announncements.size
}