package com.digicraft.westside.ui.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.managers.WestsideServiceManager
import com.digicraft.westside.models.Westside
import com.digicraft.westside.ui.ministries.MinistryFragment
import kotlinx.android.synthetic.main.fragment_events.*
import javax.inject.Inject

class EventsFragment : Fragment() {
    companion object {
        fun newInstance(showAllEvents: Boolean): EventsFragment {
            val args: Bundle = Bundle()
            args.putBoolean(WestsideConfig.SHOW_EVENTS_KEY, showAllEvents)
            val fragment = EventsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var westsideServiceManager: WestsideServiceManager
    @Inject lateinit var eventsAdapter: EventsAdapter

    var showAllEvents: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val westsideApplication = context.applicationContext as WestsideApplication
        westsideApplication.westsideComponent.inject(this)

        showAllEvents = arguments.getBoolean(WestsideConfig.SHOW_EVENTS_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsRecyclerView.adapter = eventsAdapter
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        westsideServiceManager.fetchEvents()
                .subscribe(this::onSuccess, this::onError)

    }

    fun onSuccess(eventsList: List<Westside.Event>) {
        if(showAllEvents) {
            eventsAdapter.events = eventsList
        } else {
            eventsList.forEach {
                //if (it.)
            }
        }


        eventsAdapter.notifyDataSetChanged()
        progressBar.visibility = GONE
        Log.d(EventsFragment::class.simpleName, "Events: ${eventsList.size}")
    }

    fun onError(error: Throwable) {
        Log.d(EventsFragment::class.simpleName, "Error: ${error.message}")
    }
}
