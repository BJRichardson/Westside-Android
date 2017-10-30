package com.digicraft.westside

import android.app.Application
import com.digicraft.westside.dependency_injection.DaggerWestsideComponent
import com.digicraft.westside.dependency_injection.WestsideComponent
import com.digicraft.westside.dependency_injection.WestsideModule

class WestsideApplication : Application() {
    val westsideComponent: WestsideComponent by lazy {
        DaggerWestsideComponent
                .builder()
                .westsideModule(WestsideModule(this))
                .build()
    }
}
