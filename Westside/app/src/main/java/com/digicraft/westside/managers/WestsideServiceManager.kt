package com.digicraft.westside.managers

import com.digicraft.westside.extensions.sharedNetworkSubscription
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.WestsideService
import io.reactivex.Observable

class WestsideServiceManager(val service: WestsideService) : WestsideService, Receivable.Event, Receivable.Announcement, Receivable.Prayer {
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

    override fun onEventReceived(posts: List<Westside.Event>) {
        //NOOP
    }

    override fun onAnnouncementReceived(posts: List<Westside.Announcement>) {
        //NOOP
    }

    override fun onPrayerReceived(posts: List<Westside.Prayer>) {
        //NOOP
    }
}