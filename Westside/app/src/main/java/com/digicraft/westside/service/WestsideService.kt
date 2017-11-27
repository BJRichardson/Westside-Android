package com.digicraft.westside.service

import android.util.Base64
import com.digicraft.westside.models.Westside
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.*

interface WestsideService {
    @GET(value = "groups")
    fun fetchGroups(): Observable<List<Westside.Group>>

    @GET(value = "events")
    fun fetchEvents(): Observable<List<Westside.Event>>

    @GET(value = "announcements")
    fun fetchAnnouncements(): Observable<List<Westside.Announcement>>

    @GET(value = "prayers")
    fun fetchPrayers(): Observable<List<Westside.Prayer>>

    @FormUrlEncoded
    @POST(value = "auth/token")
    fun signIn(@Field("username") emailAddress: String, @Field("password") password: String, @Field("grant_type") grantType: String = "password"): Observable<Westside.Token>

    @FormUrlEncoded
    @POST(value = "auth/token")
    fun refresh(@Field("refresh_token") refreshToken: String, @Field("grant_type") grantType: String = "refresh_token"): Observable<Westside.Token>

    @POST(value = "register")
    fun register(@Body user: Westside.New.User): Observable<Westside.Token>

    class HeaderInterceptor : Interceptor {
        companion object {
            val base64BasicAuthHeader = String(Base64.encode("com.westside.backend:fellowship1953".toByteArray(), Base64.NO_WRAP))
        }

        override fun intercept(chain: Interceptor.Chain?): Response {
            val chain = chain as Interceptor.Chain
            val alteredRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Basic $base64BasicAuthHeader")
                    .build()
            return chain.proceed(alteredRequest)
        }
    }
}
