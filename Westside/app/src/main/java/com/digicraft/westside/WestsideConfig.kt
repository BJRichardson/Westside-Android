package com.digicraft.westside

import android.util.Base64

class WestsideConfig {
    companion object {
        val BASE_URL: String = "https://westsidecme.herokuapp.com/"
        val BASE_64_BASIC_AUTH_HEADER: String = Base64.encodeToString("com.westside.backend:fellowship1953".toByteArray(), Base64.DEFAULT)
        val NUMBER_OF_TIMES_TO_RETRY: Int = 3
        val RETRY_DELAY_IN_MILLISECONDS: Long = 750
    }
}