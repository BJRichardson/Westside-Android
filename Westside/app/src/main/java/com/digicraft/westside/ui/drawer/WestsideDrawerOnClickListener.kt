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

class WestsideDrawerOnClickListener(val context: Context, val drawerLayout: DrawerLayout): NavigationView.OnNavigationItemSelectedListener {

    var user : Westside.User? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        drawerLayout.closeDrawers()
//        return false
        when {
//            item.itemId == R.id.facebook -> {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(FoxConfig.FACEBOOK_URL)
//                context.startActivity(intent)
//                return true
//            }
//            item.itemId == R.id.instagram -> {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(FoxConfig.INSTAGRAM_URL)
//                context.startActivity(intent)
//                return true
//            }
//            item.itemId == R.id.twitter -> {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(FoxConfig.TWITTER_URL)
//                context.startActivity(intent)
//                return true
//            }
//            item.itemId == R.id.contact_us -> {
//                val intent = Intent(context, ContactUsActivity::class.java)
//                context.startActivity(intent)
//                return true
//            }
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
//            item.itemId == R.id.account_details -> {
//                if (user != null) {
//                    val intent = AccountDetailsActivity.intent(context, user!!)
//                    context.startActivity(intent)
//                    return true
//                }
//                return false
//            }
            else -> return false
        }

    }
}
