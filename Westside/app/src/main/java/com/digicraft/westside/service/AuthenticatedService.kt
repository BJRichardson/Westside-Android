package com.digicraft.westside.service

import android.util.Log
import com.digicraft.westside.managers.WestsideCacheManager
import com.digicraft.westside.models.Westside
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*


interface AuthenticatedService {
    @GET(value = "me")
    fun getUser(): Observable<Westside.User>

    @POST(value = "schedule/{id}")
    fun joinEvent(@Path("id") eventId: Int): Observable<Westside.UserEvent>

    @DELETE(value = "schedule/{id}")
    fun leaveEvent(@Path("id") eventId: Int): Observable<ResponseBody>

    @POST(value = "ministries/{id}")
    fun joinMinistry(@Path("id") groupId: Int): Observable<Westside.UserGroup>

    @DELETE(value = "ministries/{id}")
    fun leaveMinistry(@Path("id") groupId: Int): Observable<ResponseBody>

//    @PUT(value = "devices")
//    fun registerDevice(@Body device: Fox.New.Device): Observable<Fox.Device>

    class HeaderInterceptor(val cacheManager: WestsideCacheManager): Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            val chain = chain as Interceptor.Chain
            Log.d("HeaderInterceptor", "${cacheManager.token?.accessToken}")
            val alteredRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${cacheManager.token?.accessToken}")
                    .build()
            return chain.proceed(alteredRequest)
        }
    }
}