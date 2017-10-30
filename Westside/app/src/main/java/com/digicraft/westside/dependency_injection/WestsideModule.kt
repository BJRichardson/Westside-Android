package com.digicraft.westside.dependency_injection

import android.app.Application
import dagger.Module

@Module
class WestsideModule(private val application: Application) {
    /**
     * Allow the application context to be injected but require that it be annotated with [ ][TestDriveApplication] to explicitly differentiate it from an activity context.
     */
//    @Provides @Singleton
//    fun provideApplication() = application
//
//    @Provides @Singleton
//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return logging
//    }
//
//    @Provides @Singleton
//    fun provideTestDriveHeaderInterceptor(): TestDriveService.HeaderInterceptor {
//        return TestDriveService.HeaderInterceptor()
//    }
//
//    @Provides
//    fun providesOkHttpBuilder(httpLoggingInterceptor: HttpLoggingInterceptor, application: Application): OkHttpClient.Builder {
//        val builder = OkHttpClient.Builder()
//        builder.addInterceptor(httpLoggingInterceptor)
//        // Set the cache location and size (5 MB)
//        builder.cache(Cache(File(application.cacheDir, "apiResponses"), 5 * 1024 * 1024))
//        return builder
//    }
//    @Provides @Singleton
//    fun provideTestDriveService(builder: OkHttpClient.Builder, headerInterceptor: TestDriveService.HeaderInterceptor): TestDriveService {
//
//        builder.addInterceptor(headerInterceptor)
//
//        val retrofit = Retrofit.Builder()
//                .client(builder.build())
//                .baseUrl(TestDriveConfig.BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        return retrofit.create(TestDriveService::class.java)
//    }
//
//    @Provides @Singleton
//    fun provideSharedPreferences(application: Application): SharedPreferences {
//        return application.getSharedPreferences("testDrivePreferences", Application.MODE_PRIVATE)
//    }
//
//    @Provides @Singleton
//    fun providesTestDriveServiceManager(service: TestDriveService): TestDriveServiceManager {
//        return TestDriveServiceManager(service)
//    }
//
//    @Provides
//    fun providesFeedAdapter(): FeedAdapter {
//        return FeedAdapter (ArrayList<TestDrive.Post>())
//    }
}
