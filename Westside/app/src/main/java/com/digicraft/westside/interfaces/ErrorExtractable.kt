package com.digicraft.westside.interfaces

import android.content.res.Resources
import com.digicraft.westside.R
import com.digicraft.westside.models.Westside
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException

interface ErrorExtractable {
    fun errorTextFromException(throwable: Throwable, resources: Resources): String {
        return if (throwable is HttpException) {
            errorFromHttpException(throwable).error
        } else {
            resources.getString(R.string.something_went_wrong) //catchall
        }
    }
}

fun errorFromHttpException(exception: HttpException): Westside.Error {
    val gsonBuilder = GsonBuilder().create()
    val type = object : TypeToken<Westside.Error>() {}.type
    return gsonBuilder.fromJson(exception.response().errorBody()?.string(), type)
}