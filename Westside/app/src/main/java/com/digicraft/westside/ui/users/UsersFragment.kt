package com.digicraft.westside.ui.users

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.managers.WestsideServiceManager
import com.digicraft.westside.models.Westside
import com.digicraft.westside.ui.announcements.AnnouncementsFragment
import kotlinx.android.synthetic.main.fragment_ministries.*
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class UsersFragment : Fragment() {

    @Inject lateinit var westsideServiceManager: AuthenticatedServiceManager
    @Inject lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = context.applicationContext as WestsideApplication
        application.westsideComponent.inject(this)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ministrisRecyclerView.adapter = usersAdapter
        ministrisRecyclerView.layoutManager = LinearLayoutManager(context)

        setupToolbar((activity as UsersActivity), customToolbar)
    }

    override fun onResume() {
        super.onResume()
        westsideServiceManager.fetchUsers()
                .subscribe(this::onSuccess, this::onError)
    }

    fun onSuccess(userssList: List<Westside.User>) {
        usersAdapter.users = userssList
        usersAdapter.notifyDataSetChanged()
        ministriesProgressBar.visibility = View.GONE
        Log.d(UsersFragment::class.simpleName, "Events: ${userssList.size}")
    }

    fun onError(error: Throwable) {
        Log.d(AnnouncementsFragment::class.simpleName, "Error: ${error.message}")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity.finish()
            return true
        }
        return false
    }
}
