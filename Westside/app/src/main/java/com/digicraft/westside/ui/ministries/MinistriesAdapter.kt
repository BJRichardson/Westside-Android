package com.digicraft.westside.ui.ministries

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.digicraft.westside.databinding.ViewMinistryBinding
import com.digicraft.westside.models.Westside

class MinistriesAdapter(ministriesList: List<Westside.Group>) : RecyclerView.Adapter<MinistriesViewHolder>() {
    var groups: List<Westside.Group> = ministriesList

    override fun onBindViewHolder(holder: MinistriesViewHolder?, position: Int) {
        holder?.binding?.vm = MinistriesViewModel(groups[position], holder?.itemView?.context)
        Log.d("FeedAdapter", "${groups[position].name}")
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MinistriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ViewMinistryBinding.inflate(layoutInflater, parent, false)
        val viewHolder = MinistriesViewHolder(binding, parent?.context)
        viewHolder.itemView.setOnClickListener(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = groups.size
}
