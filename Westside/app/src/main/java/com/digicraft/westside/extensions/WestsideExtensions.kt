package com.digicraft.westside.extensions

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.dependency_injection.WestsideComponent

fun AppCompatActivity.westsideComponent(): WestsideComponent {
    return (applicationContext as WestsideApplication).westsideComponent
}

fun AppCompatActivity.setupToolbar(toolBar: Toolbar, showBack: Boolean = true) {
    setSupportActionBar(toolBar)
    if (showBack) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}