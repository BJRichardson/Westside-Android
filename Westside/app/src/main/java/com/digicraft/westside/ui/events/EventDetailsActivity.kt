package com.digicraft.westside.ui.events


import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.databinding.ActivityEventDetailsBinding
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.AuthenticatedServiceManager
import com.digicraft.westside.models.Westside
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class EventDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var serviceManager: AuthenticatedServiceManager

    private lateinit var event: Westside.Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        val westsideApplication = applicationContext as WestsideApplication
        westsideApplication.westsideComponent.inject(this)

        event = intent.getParcelableExtra<Westside.Event>(WestsideConfig.EVENT_KEY)

        val binding = DataBindingUtil.setContentView<ActivityEventDetailsBinding>(this, R.layout.activity_event_details)
        binding.vm = EventDetailsViewModel(serviceManager, event, this)

        setupToolbar(customToolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }
}
