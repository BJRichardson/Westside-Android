package com.digicraft.westside.ui.account

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.databinding.ActivityCreateAccountBinding
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.WestsideServiceManager
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject


class CreateAccountActivity: AppCompatActivity() {

    @Inject
    lateinit var service: WestsideServiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        val foxApplication = applicationContext as WestsideApplication
        foxApplication.westsideComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityCreateAccountBinding>(this, R.layout.activity_create_account)
        binding.vm = CreateAccountViewModel(resources, service, this)

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
