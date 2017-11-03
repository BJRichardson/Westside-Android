package com.digicraft.westside.dependency_injection

import dagger.Component
import javax.inject.Singleton
import com.digicraft.westside.MainActivity
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.ui.events.EventsFragment


@Singleton
@Component(modules = arrayOf(WestsideModule::class))
interface WestsideComponent {
    fun inject(application: WestsideApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(eventFragment: EventsFragment)
}