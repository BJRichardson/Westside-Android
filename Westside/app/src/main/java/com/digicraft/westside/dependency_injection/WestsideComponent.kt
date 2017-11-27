package com.digicraft.westside.dependency_injection

import dagger.Component
import javax.inject.Singleton
import com.digicraft.westside.MainActivity
import com.digicraft.westside.WestsideApplication
import com.digicraft.westside.ui.account.CreateAccountActivity
import com.digicraft.westside.ui.account.SignInActivity
import com.digicraft.westside.ui.announcements.AnnouncementsFragment
import com.digicraft.westside.ui.events.EventDetailsActivity
import com.digicraft.westside.ui.events.EventsFragment
import com.digicraft.westside.ui.ministries.MinistriesFragment
import com.digicraft.westside.ui.ministries.MinistryFragment
import com.digicraft.westside.ui.prayers.PrayersFragment


@Singleton
@Component(modules = arrayOf(WestsideModule::class))
interface WestsideComponent {
    fun inject(application: WestsideApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(eventFragment: EventsFragment)
    fun inject(announcementsFragment: AnnouncementsFragment)
    fun inject(prayersFragment: PrayersFragment)
    fun inject(signInActivity: SignInActivity)
    fun inject(createAccountActivity: CreateAccountActivity)
    fun inject(eventDetailsActivity: EventDetailsActivity)
    fun inject(ministriesFragment: MinistriesFragment)
    fun inject(ministryFragment: MinistryFragment)
}