package com.digicraft.westside.ui.users

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.digicraft.westside.databinding.ViewUserBinding
import com.digicraft.westside.models.Westside

class UsersAdapter(usersList: List<Westside.User>) : RecyclerView.Adapter<UsersViewHolder>() {
    var users: List<Westside.User> = usersList

    override fun onBindViewHolder(holder: UsersViewHolder?, position: Int) {
        holder?.binding?.vm = UsersViewModel(users[position], holder?.itemView?.context)
        Log.d("FeedAdapter", "${users[position].firstName}")
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ViewUserBinding.inflate(layoutInflater, parent, false)
        val viewHolder = UsersViewHolder(binding, parent?.context)
        viewHolder.itemView.setOnClickListener(viewHolder)
        return viewHolder
    }

    override fun getItemCount(): Int = users.size
}

