package com.digicraft.westside.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.digicraft.westside.MainActivity
import com.digicraft.westside.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, MainActivity::class.java)
        val handler = Handler()
        handler.postDelayed(Runnable {
            startActivity(intent)
        }, 1000)
    }
}
