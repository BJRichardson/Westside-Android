package com.digicraft.westside.ui.drawer

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.digicraft.westside.MainActivity
import com.digicraft.westside.R
import com.digicraft.westside.models.Westside
import com.digicraft.westside.ui.account.CreateAccountActivity
import com.digicraft.westside.ui.account.SignInActivity
import com.digicraft.westside.ui.ministries.MinistriesActivity
import com.digicraft.westside.ui.users.UsersActivity

class WestsideDrawerOnClickListener(val context: Context, val drawerLayout: DrawerLayout): NavigationView.OnNavigationItemSelectedListener {

    var user : Westside.User? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == R.id.create_account -> {
                val intent = Intent(context, CreateAccountActivity::class.java)
                context.startActivity(intent)
                return true
            }
            item.itemId == R.id.sign_in -> {
                val intent = Intent(context, SignInActivity::class.java)
                context.startActivity(intent)
                return true
            }
            item.itemId == R.id.sign_out -> {
                (context as MainActivity).onLogoutClicked()
                return true
            }
            item.itemId == R.id.ministries -> {
                val intent = Intent(context, MinistriesActivity::class.java)
                context.startActivity(intent)
                return true
            }
            item.itemId == R.id.members -> {
                val intent = Intent(context, UsersActivity::class.java)
                context.startActivity(intent)
                return true
            }
            item.itemId == R.id.my_events -> {
                val intent = Intent(context, UsersActivity::class.java)
                context.startActivity(intent)
                return true
            }
            else -> return false
        }

    }
}
