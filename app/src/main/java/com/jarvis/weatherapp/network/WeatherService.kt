package com.jarvis.weatherapp.network

import com.jarvis.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(PATH_WEATHER_INFO)
    suspend fun getWeatherFromLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String = RetrofitClient.API_KEY ?: "",
        @Query("units") units: String = "Metric"
    ): Response<WeatherResponse>

    @GET(PATH_WEATHER_INFO)
    suspend fun getWeatherFromCityName(
        @Query("q") q: String,
        @Query("appid") appId: String = RetrofitClient.API_KEY ?: "",
        @Query("units") units: String = "Metric"
    ): Response<WeatherResponse>

    @GET(PATH_WEATHER_INFO)
    suspend fun getWeatherFromZipCode(
        @Query("zip") q: String,
        @Query("appid") appId: String = RetrofitClient.API_KEY ?: "",
        @Query("units") units: String = "Metric"
    ): Response<WeatherResponse>

    companion object {
        const val PATH_WEATHER_INFO = "data/2.5/weather"
    }
}