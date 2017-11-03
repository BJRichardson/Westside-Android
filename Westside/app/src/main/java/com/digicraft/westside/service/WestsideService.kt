package com.digicraft.westside.service

import android.util.Base64
import com.digicraft.westside.models.Westside
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.*

interface WestsideService {
    @GET(value = "events")
    fun fetchEvents(): Observable<List<Westside.Event>>

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
