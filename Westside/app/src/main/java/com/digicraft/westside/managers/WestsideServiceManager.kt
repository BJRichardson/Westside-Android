package com.digicraft.westside.managers

import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.extensions.sharedNetworkSubscription
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.RetryAfterTimeoutWithDelay
import com.digicraft.westside.service.WestsideService
import io.reactivex.Observable

class WestsideServiceManager(val service: WestsideService, private val cacheManager: WestsideCacheManager) : WestsideService, Receivable.Event, Receivable.Announcement, Receivable.Prayer, Receivable.Token, Receivable.Group {
    override fun fetchGroups(): Observable<List<Westside.Group>> {
        val observable = service.fetchGroups()
                .sharedNetworkSubscription()
        observable.subscribe(this::onGroupsReceived, this::onGroupReceivedError)
        return observable
    }

    override fun fetchEvents(): Observable<List<Westside.Event>> {
        val observable = service.fetchEvents()
                .sharedNetworkSubscription()
        observable.subscribe(this::onEventReceived, this::onEventReceivedError)
        return observable
    }

    override fun fetchAnnouncements(): Observable<List<Westside.Announcement>> {
        val observable = service.fetchAnnouncements()
                .sharedNetworkSubscription()
        observable.subscribe(this::onAnnouncementReceived, this::onAnnouncementReceivedError)
        return observable
    }

    override fun fetchPrayers(): Observable<List<Westside.Prayer>> {
        val observable = service.fetchPrayers()
                .sharedNetworkSubscription()
        observable.subscribe(this::onPrayerReceived, this::onPrayerReceivedError)
        return observable
    }

    override fun signIn(emailAddress: String, password: String, grantType: String): Observable<Westside.Token> {
        val observable = service.signIn(emailAddress, password, grantType)
                .sharedNetworkSubscription()
                .retryWhen(RetryAfterTimeoutWithDelay(WestsideConfig.NUMBER_OF_TIMES_TO_RETRY, WestsideConfig.RETRY_DELAY_IN_MILLISECONDS))
        observable.subscribe(this::onTokenReceived, this::onTokenReceivedError)
        return observable
    }

    override fun refresh(refreshToken: String, grantType: String): Observable<Westside.Token> {
        val observable = service.refresh(refreshToken, grantType)
                .sharedNetworkSubscription()
                .retryWhen(RetryAfterTimeoutWithDelay(WestsideConfig.NUMBER_OF_TIMES_TO_RETRY, WestsideConfig.RETRY_DELAY_IN_MILLISECONDS))
        observable.subscribe(this::onTokenReceived, this::onTokenReceivedError)
        return observable
    }

    override fun register(user: Westside.New.User): Observable<Westside.Token> {
        val observable = service.register(user)
                .sharedNetworkSubscription()
                .retryWhen(RetryAfterTimeoutWithDelay(WestsideConfig.NUMBER_OF_TIMES_TO_RETRY, WestsideConfig.RETRY_DELAY_IN_MILLISECONDS))
        observable.subscribe(this::onTokenReceived, this::onTokenReceivedError)
        return observable
    }

    override fun onTokenReceived(token: Westside.Token) {
        //cache the token & store in memory
        cacheManager.cache(token)
    }
}