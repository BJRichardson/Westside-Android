package com.digicraft.westside.dependency_injection

import android.app.Application
import android.content.SharedPreferences
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.managers.WestsideServiceManager
import com.digicraft.westside.models.Westside
import com.digicraft.westside.service.WestsideService
import com.digicraft.westside.ui.announcements.AnnouncementsAdapter
import com.digicraft.westside.ui.events.EventsAdapter
import com.digicraft.westside.ui.prayers.PrayersAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class WestsideModule(private val application: Application) {
    /**
     * Allow the application context to be injected but require that it be annotated with [ ][TestDriveApplication] to explicitly differentiate it from an activity context.
     */
    @Provides @Singleton
    fun provideApplication() = application

    @Provides @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides @Singleton
    fun provideTestDriveHeaderInterceptor(): WestsideService.HeaderInterceptor {
        return WestsideService.HeaderInterceptor()
    }

    @Provides
    fun providesOkHttpBuilder(httpLoggingInterceptor: HttpLoggingInterceptor, application: Application): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLoggingInterceptor)
        // Set the cache location and size (5 MB)
        builder.cache(Cache(File(application.cacheDir, "apiResponses"), 5 * 1024 * 1024))
        return builder
    }

    @Provides @Singleton
    fun provideTestDriveService(builder: OkHttpClient.Builder, headerInterceptor: WestsideService.HeaderInterceptor): WestsideService {

        builder.addInterceptor(headerInterceptor)

        val retrofit = Retrofit.Builder()
                .client(builder.build())
                .baseUrl(WestsideConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(WestsideService::class.java)
    }

    @Provides @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("testDrivePreferences", Application.MODE_PRIVATE)
    }

    @Provides @Singleton
    fun providesTestDriveServiceManager(service: WestsideService): WestsideServiceManager {
        return WestsideServiceManager(service)
    }

    @Provides
    fun providesFeedAdapter(): EventsAdapter {
        return EventsAdapter (ArrayList<Westside.Event>())
    }

    @Provides
    fun providesAnnouncementsAdapter(): AnnouncementsAdapter {
        return AnnouncementsAdapter (ArrayList<Westside.Announcement>())
    }

    @Provides
    fun providesPrayersAdapter(): PrayersAdapter {
        return PrayersAdapter (ArrayList<Westside.Prayer>())
    }
}
