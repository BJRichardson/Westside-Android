package com.digicraft.westside.ui.account

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.digicraft.westside.R
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.databinding.ActivitySignInBinding
import com.digicraft.westside.extensions.setupToolbar
import com.digicraft.westside.managers.WestsideServiceManager
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject


class SignInActivity: AppCompatActivity() {

    @Inject
    lateinit var service: WestsideServiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        val westsideApplication = applicationContext as WestsideApplication
        westsideApplication.westsideComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
        binding.vm = SignInViewModel(service, resources, this)

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
