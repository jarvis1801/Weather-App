package com.jarvis.weatherapp.viewModel.search

import com.jarvis.weatherapp.base.datasource.DataResponse
import com.jarvis.weatherapp.base.repository.BaseRepository
import com.jarvis.weatherapp.dataSource.WeatherRemoteDataSource
import com.jarvis.weatherapp.model.WeatherResponse

class SearchRepository(
    private val remoteDataSource: WeatherRemoteDataSource
) : BaseRepository() {

    suspend fun getWeatherFromLocation(lat: String, lon: String): DataResponse<WeatherResponse> {
        return performRequest { remoteDataSource.getWeatherFromLocation(lat, lon) }
    }

    suspend fun getWeatherFromCityName(cityName: String): DataResponse<WeatherResponse> {
        return performRequest { remoteDataSource.getWeatherFromCityName(cityName) }
    }

    suspend fun getWeatherFromZipCode(zipCode: String): DataResponse<WeatherResponse> {
        return performRequest { remoteDataSource.getWeatherFromZipCode(zipCode) }
    }
}