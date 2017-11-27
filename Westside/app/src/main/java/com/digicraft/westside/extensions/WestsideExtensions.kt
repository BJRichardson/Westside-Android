package com.digicraft.westside.extensions


import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.dependency_injection.WestsideComponent
import com.digicraft.westside.service.RetryAfterTimeoutWithDelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun AppCompatActivity.westsideComponent(): WestsideComponent {
    return (applicationContext as WestsideApplication).westsideComponent
}

fun AppCompatActivity.setupToolbar(toolBar: Toolbar, showBack: Boolean = true) {
    setSupportActionBar(toolBar)
    if (showBack) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

fun Fragment.setupToolbar(activity: AppCompatActivity, toolBar: Toolbar, showBack: Boolean = true) {
    activity.setSupportActionBar(toolBar)
    if (showBack) {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

fun <T> Observable<T>.sharedNetworkSubscription(): Observable<T> {
    return this.share()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .retryWhen(RetryAfterTimeoutWithDelay(WestsideConfig.NUMBER_OF_TIMES_TO_RETRY, WestsideConfig.RETRY_DELAY_IN_MILLISECONDS))
}