package com.digicraft.westside.ui.ministries

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
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.models.Westside
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class MinistryFragment : Fragment() {
    companion object {
        fun newInstance(group: Westside.Group?) : MinistryFragment {
            val args: Bundle = Bundle()
            args.putParcelable(WestsideConfig.GROUP_KEY, group)
            val fragment = MinistryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var serviceManager: AuthenticatedServiceManager

    lateinit var group: Westside.Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = context.applicationContext as WestsideApplication
        application.westsideComponent.inject(this)

        setHasOptionsMenu(true)

        group = arguments.getParcelable<Westside.Group>(WestsideConfig.GROUP_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMinistryBinding>(inflater, R.layout.fragment_ministry,container , false)
        binding.vm = MinistryViewModel(group, serviceManager, activity)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupToolbar((activity as MinistriesActivity), customToolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity.supportFragmentManager.popBackStack()
            return true
        }
        return false
    }
}