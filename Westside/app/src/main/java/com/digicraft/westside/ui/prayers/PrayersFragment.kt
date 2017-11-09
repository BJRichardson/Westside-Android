package com.digicraft.westside.ui.prayers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.managers.WestsideServiceManager
import com.digicraft.westside.models.Westside
import kotlinx.android.synthetic.main.fragment_prayers.*
import javax.inject.Inject

class PrayersFragment : Fragment() {

    @Inject lateinit var westsideServiceManager: WestsideServiceManager
    @Inject lateinit var prayersAdapter: PrayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val testDriveApplication = context.applicationContext as WestsideApplication
        testDriveApplication.westsideComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_prayers, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prayersRecyclerView.adapter = prayersAdapter
        prayersRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        westsideServiceManager.fetchPrayers()
                .subscribe(this::onSuccess, this::onError)
    }

    fun onSuccess(prayersList: List<Westside.Prayer>) {
        prayersAdapter.prayers = prayersList
        prayersAdapter.notifyDataSetChanged()
        Log.d(PrayersFragment::class.simpleName, "Events: ${prayersList.size}")
    }

    fun onError(error: Throwable) {
        Log.d(PrayersFragment::class.simpleName, "Error: ${error.message}")
    }

}
