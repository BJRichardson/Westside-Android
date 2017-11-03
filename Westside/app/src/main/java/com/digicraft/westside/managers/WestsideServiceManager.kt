package com.digicraft.westside.managers

import com.digicraft.westside.extensions.sharedNetworkSubscription
import com.digicraft.westside.interfaces.Receivable
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.WestsideService
import io.reactivex.Observable

class WestsideServiceManager(val service: WestsideService) : WestsideService, Receivable.Event {

    override fun fetchEvents(): Observable<List<Westside.Event>> {
        val observable = service.fetchEvents()
                .sharedNetworkSubscription()
        observable.subscribe(this::onEventReceived, this::onEventReceivedError)
        return observable
    }


    override fun onEventReceived(posts: List<Westside.Event>) {
        //NOOP
    }
}