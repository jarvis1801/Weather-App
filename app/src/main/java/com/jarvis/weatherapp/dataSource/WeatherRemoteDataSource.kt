package com.jarvis.weatherapp.dataSource

import com.jarvis.weatherapp.base.datasource.BaseDataSource
import com.jarvis.weatherapp.base.datasource.DataResponse
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.network.RetrofitClient
import com.jarvis.weatherapp.network.WeatherService

class WeatherRemoteDataSource : BaseDataSource() {

    suspend fun getWeatherFromLocation(lat: String, lon: String): DataResponse<WeatherResponse> {
        return getResult {
            RetrofitClient()
                .getService<WeatherService>()
                .getWeatherFromLocation(lat, lon)
        }
    }
    suspend fun getWeatherFromCityName(cityName: String): DataResponse<WeatherResponse> {
        return getResult {
            RetrofitClient()
                .getService<WeatherService>()
                .getWeatherFromCityName(cityName)
        }
    }

    suspend fun getWeatherFromZipCode(zipCode: String): DataResponse<WeatherResponse> {
        return getResult {
            RetrofitClient()
                .getService<WeatherService>()
                .getWeatherFromZipCode(zipCode)
        }
    }
}