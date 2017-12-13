package com.digicraft.westside.ui.users

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.digicraft.westside.R
import com.digicraft.westside.databinding.ViewUserBinding

class UsersViewHolder(val binding: ViewUserBinding, val context: Context?) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    override fun onClick(view: View?) {
        val activity = context as UsersActivity

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, UserFragment.newInstance(binding.vm?.user))
                .addToBackStack(null)
                .commit();
    }
}