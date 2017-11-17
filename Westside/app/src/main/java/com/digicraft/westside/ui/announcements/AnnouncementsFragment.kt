package com.digicraft.westside.ui.announcements


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
import com.digicraft.westside.managers.WestsideServiceManager
import com.digicraft.westside.models.Westside
import kotlinx.android.synthetic.main.fragment_announcements.*
import javax.inject.Inject

class AnnouncementsFragment : Fragment() {

    @Inject lateinit var westsideServiceManager: WestsideServiceManager
    @Inject lateinit var announcementsAdapter: AnnouncementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val testDriveApplication = context.applicationContext as WestsideApplication
        testDriveApplication.westsideComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_announcements, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announcementsRecyclerView.adapter = announcementsAdapter
        announcementsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        westsideServiceManager.fetchAnnouncements()
                .subscribe(this::onSuccess, this::onError)
    }

    fun onSuccess(announcementsList: List<Westside.Announcement>) {
        announcementsAdapter.announncements = announcementsList
        announcementsAdapter.notifyDataSetChanged()
        progressBar.visibility = GONE
        Log.d(AnnouncementsFragment::class.simpleName, "Events: ${announcementsList.size}")
    }

    fun onError(error: Throwable) {
        Log.d(AnnouncementsFragment::class.simpleName, "Error: ${error.message}")
    }
}