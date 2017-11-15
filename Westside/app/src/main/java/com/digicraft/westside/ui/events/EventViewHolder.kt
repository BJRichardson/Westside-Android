package com.digicraft.westside.ui.events

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.databinding.ViewEventBinding

class EventViewHolder(val binding: ViewEventBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    override fun onClick(view: View?) {
        val intent = Intent(view?.context, EventDetailsActivity::class.java)
        intent.putExtra(WestsideConfig.EVENT_KEY, binding.vm?.event)
        view?.context?.startActivity(intent)
    }
}
