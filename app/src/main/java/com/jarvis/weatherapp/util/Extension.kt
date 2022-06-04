package com.jarvis.weatherapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Extension {
    inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object : TypeToken<T>() {}.type)

    inline fun <reified T> List<T>?.toArrayList() : ArrayList<T> {
        return this?.toCollection(ArrayList()) ?: arrayListOf()
    }
}