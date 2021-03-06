package com.digicraft.westside

import android.util.Base64

class WestsideConfig {
    companion object {
        val BASE_URL: String = "https://westsidecme.herokuapp.com/"
        val BASE_64_BASIC_AUTH_HEADER: String = Base64.encodeToString("com.westside.backend:fellowship1953".toByteArray(), Base64.DEFAULT)
        val NUMBER_OF_TIMES_TO_RETRY: Int = 3
        val RETRY_DELAY_IN_MILLISECONDS: Long = 750
        val TOKEN_KEY: String = "fox_token"
        val DEFAULT_TOKEN: String = "INVALID_TOKEN"
        val EVENT_KEY: String = "event"
        val GROUP_KEY: String = "group"
        val USER_KEY: String = "user"
        val SHOW_EVENTS_KEY: String = "showAllEvents"
        val PLACEHOLDER_EVENT_URL: String = "http://something"
    }
}