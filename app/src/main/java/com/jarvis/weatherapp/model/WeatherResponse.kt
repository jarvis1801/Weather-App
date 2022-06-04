package com.jarvis.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherResponse (
	val coord : Coord? = null,
	val weather : List<Weather>? = null,
	val base : String? = null,
	val main : Main? = null,
	val visibility : Int? = null,
	val wind : Wind? = null,
	val clouds : Clouds? = null,
	val dt : Int? = null,
	val sys : Sys? = null,
	val timezone : Int? = null,
	@PrimaryKey val id : Int? = null,
	val name : String? = null,
	val cod : Int? = null,
	val type: Int? = null,
	var updatedAt: Long? = null
) {
	companion object {
		const val TYPE_LOCATION = 0
		const val TYPE_CITY_NAME_OR_ZIP_CODE = 1
	}
}

data class Clouds (
	val all : Int
)

data class Coord (
	val lon : Double,
	val lat : Double
)

data class Main (
	val temp : Double,
	val feels_like : Double,
	val temp_min : Double,
	val temp_max : Double,
	val pressure : Int,
	val humidity : Int
)

data class Sys (
	val type : Int,
	val id : Int,
	val message : Double,
	val country : String,
	val sunrise : Int,
	val sunset : Int
)

data class Weather (
	val id : Int,
	val main : String,
	val description : String,
	val icon : String
)

data class Wind (
	val speed : Double,
	val deg : Int
)