package com.digicraft.westside.ui.ministries

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.digicraft.westside.R
import com.digicraft.westside.databinding.ViewMinistryBinding

class MinistriesViewHolder(val binding: ViewMinistryBinding, val context: Context?) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

//    init {
//
//        val chair = binding.root.findViewById<TextView>(R.id.chairperson_textView)
//        chair.setOnClickListener({
//            Log.d("CLICK", "WORKs")
//        })
//
//        val button = binding.root.findViewById<Button>(R.id.test_button)
//        button.setOnClickListener({
//            Log.d("Button", "WORKs")
//        })
//    }

    override fun onClick(view: View?) {
        val activity = context as MinistriesActivity

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, MinistryFragment.newInstance(binding.vm?.group))
                .addToBackStack(null)
                .commit();
    }
}