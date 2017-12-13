package com.digicraft.westside

import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.digicraft.westside.adapters.TabbedPagerAdapter
import com.digicraft.westside.databinding.ActivityMainBinding
import com.digicraft.westside.databinding.ViewDrawerHeaderBinding
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.extensions.westsideComponent
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.managers.ReauthenticationListener
import com.digicraft.westside.managers.WestsideCacheManager
import com.digicraft.westside.models.Westside
import com.digicraft.westside.ui.drawer.HeaderViewModel
import com.digicraft.westside.ui.drawer.WestsideDrawerOnClickListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Receivable.User, ReauthenticationListener {
    private lateinit var drawerToggle: ActionBarDrawerToggle

    @Inject
    lateinit var serviceManager: AuthenticatedServiceManager

    @Inject
    lateinit var cacheManager: WestsideCacheManager

    private val headerViewModel = HeaderViewModel()
    private lateinit var westsideDrawerListener: WestsideDrawerOnClickListener

    private lateinit var adapter: TabbedPagerAdapter

    var onBackPressListener: OnBackPressed? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        westsideComponent().inject(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        val mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val drawerBinding = DataBindingUtil.inflate<ViewDrawerHeaderBinding>(layoutInflater, R.layout.view_drawer_header, mainBinding.navigation, false)
        mainBinding.navigation.addHeaderView(drawerBinding.root)
        drawerBinding.vm = headerViewModel

        setupToolbar(customToolbar, false)

        adapter = TabbedPagerAdapter(supportFragmentManager, applicationContext)
        tabbedViewPager.adapter = adapter
        tabbedViewPager.offscreenPageLimit = adapter.count
        tabLayout.setupWithViewPager(tabbedViewPager)

        westsideDrawerListener = WestsideDrawerOnClickListener(this, drawerLayout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, customToolbar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle.isDrawerIndicatorEnabled = true
        supportActionBar?.setDisplayShowHomeEnabled(true)
        navigation.setNavigationItemSelectedListener(westsideDrawerListener)

        drawerLayout.addDrawerListener(drawerToggle)
    }

    override fun onResume() {
        super.onResume()
        //FirebaseInstanceId.getInstance().getToken()
        serviceManager.addReauthenticationListener(this)
        getUser()
    }

    private fun getUser() {
        val observable = serviceManager.getUser()
        observable.subscribe(headerViewModel::onUserRecieved, headerViewModel::onError)
        observable.subscribe(this::onUserReceived, this::onUserReceivedError)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState()
        // Sync auth state of drawer
        setOptionsMenuAuthenticatedState(navigation.menu, serviceManager.isAuthenticated)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        onBackPressListener?.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOptionsMenuAuthenticatedState(theMenu: Menu?, isAuthenticated: Boolean) {
        navigation.menu.setGroupVisible(R.id.authedGroup, !isAuthenticated)
        navigation.menu.setGroupVisible(R.id.connectGroup, isAuthenticated)
        navigation.menu.setGroupVisible(R.id.unauthedGroup, isAuthenticated)
    }

    override fun onUserReceived(user: Westside.User) {
        westsideDrawerListener.user = user
        setOptionsMenuAuthenticatedState(navigation.menu, serviceManager.isAuthenticated)
    }

    override fun onUserReceivedError(error: Throwable) {
        super.onUserReceivedError(error)
        setOptionsMenuAuthenticatedState(navigation.menu, serviceManager.isAuthenticated)
    }

    override fun onReauthenticated(token: Westside.Token) {
        getUser()
    }

    fun onLogoutClicked() {
        cacheManager.clearToken()
        setOptionsMenuAuthenticatedState(navigation.menu, serviceManager.isAuthenticated)
        headerViewModel.onSignedOut()
    }
}

interface OnBackPressed {
    fun onBackPressed()
}