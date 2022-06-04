package com.jarvis.weatherapp.dataSource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jarvis.weatherapp.model.*
import com.jarvis.weatherapp.util.Extension.fromJson

class GenericDataConverter {

    @TypeConverter
    fun fromWeatherResponseObj(obj: WeatherResponse?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toWeatherResponseObj(string: String?): WeatherResponse? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromWeatherListObj(obj: List<Weather>?): String {
        return obj.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toWeatherListObj(string: String): List<Weather>? {
        return string.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromWindObj(obj: Wind?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toWindObj(string: String?): Wind? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromCloudsObj(obj: Clouds?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toCloudsObj(string: String?): Clouds? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromSysObj(obj: Sys?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toSysObj(string: String?): Sys? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromCoordObj(obj: Coord?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toCoordObj(string: String?): Coord? {
        return string?.let { Gson().fromJson(string) }
    }

    @TypeConverter
    fun fromMainObj(obj: Main?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    @TypeConverter
    fun toMainObj(string: String?): Main? {
        return string?.let { Gson().fromJson(string) }
    }
}