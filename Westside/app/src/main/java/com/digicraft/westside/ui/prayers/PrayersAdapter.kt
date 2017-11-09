package com.digicraft.westside.ui.prayers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.digicraft.westside.databinding.ViewPrayerBinding

import com.digicraft.westside.models.Westside


class PrayersAdapter(prayersList: List<Westside.Prayer>) : RecyclerView.Adapter<PrayerViewHolder>() {
    var prayers: List<Westside.Prayer> = prayersList

    override fun onBindViewHolder(holder: PrayerViewHolder?, position: Int) {
        holder?.binding?.vm = PrayerViewModel(prayers[position], holder?.itemView?.context)
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PrayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ViewPrayerBinding.inflate(layoutInflater, parent, false)
        val viewHolder = PrayerViewHolder(binding)
        viewHolder.itemView.setOnClickListener(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = prayers.size
}