package com.digicraft.westside.managers

import android.content.SharedPreferences
import com.digicraft.westside.WestsideConfig
import com.digicraft.westside.models.Westside


class WestsideCacheManager(private val preferences: SharedPreferences) {

    val isAuthenticated: Boolean
        get() = refreshToken != WestsideConfig.DEFAULT_TOKEN

    var refreshToken: String = preferences.getString(WestsideConfig.TOKEN_KEY, WestsideConfig.DEFAULT_TOKEN)
    var token: Westside.Token? = null

    fun cache(token: Westside.Token) {
        refreshToken = token.refreshToken
        this.token = token
        preferences.edit().putString(WestsideConfig.TOKEN_KEY, token.refreshToken).apply()
    }

    fun clearToken() {
        this.refreshToken = WestsideConfig.DEFAULT_TOKEN
        this.token = null
        preferences.edit().remove(WestsideConfig.TOKEN_KEY).apply()
    }
}