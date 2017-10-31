package com.digicraft.westside.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.digicraft.westside.R
import java.lang.IllegalStateException


class TabbedPagerAdapter(fm: FragmentManager, val applicationContext: Context) : FragmentPagerAdapter(fm) {

    /*
    https://stackoverflow.com/questions/9727173/support-fragmentpageradapter-holds-reference-to-old-fragments/9745935#9745935
    Turns out it is incredibly important to not instantiate fragments outside this method if you are using supportFragmentManager
    or else you will get a memory leak
     */
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Fragment()
            1 -> Fragment()
            2 -> Fragment()
            3 -> Fragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        val fragment = getItem(position)
//        return when (fragment) {
//            is TicketListFragment -> applicationContext.getString(R.string.services)
//            is BarContainerFragment -> applicationContext.getString(R.string.events)
//            is FoxLocationsFragment -> applicationContext.getString(R.string.announcements)
//            is FoxLocationsFragment -> applicationContext.getString(R.string.prayers)
        return when (position) {
            0 -> applicationContext.getString(R.string.services)
            1 -> applicationContext.getString(R.string.events)
            2 -> applicationContext.getString(R.string.announcements)
            3 -> applicationContext.getString(R.string.prayers)
            else -> throw IllegalStateException()
        }
    }
}
