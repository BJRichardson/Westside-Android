package com.digicraft.westside.ui.users

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.databinding.FragmentMinistryBinding
import com.digicraft.westside.databinding.FragmentUserBinding
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.models.Westside
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class UserFragment : Fragment() {
    companion object {
        fun newInstance(user: Westside.User?) : UserFragment {
            val args: Bundle = Bundle()
            args.putParcelable(WestsideConfig.USER_KEY, user)
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var serviceManager: AuthenticatedServiceManager

    lateinit var user: Westside.User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = context.applicationContext as WestsideApplication
        application.westsideComponent.inject(this)

        setHasOptionsMenu(true)

        user = arguments.getParcelable<Westside.User>(WestsideConfig.USER_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user,container , false)
        binding.vm = UserViewModel(user, serviceManager, activity)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupToolbar((activity as UsersActivity), customToolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity.supportFragmentManager.popBackStack()
            return true
        }
        return false
    }
}
