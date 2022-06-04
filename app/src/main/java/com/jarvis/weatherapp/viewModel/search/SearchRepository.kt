package com.jarvis.weatherapp.viewModel.search

import com.jarvis.weatherapp.base.datasource.DataResponse
import com.jarvis.weatherapp.base.repository.BaseRepository
import com.jarvis.weatherapp.dataSource.WeatherDao
import com.jarvis.weatherapp.dataSource.WeatherRemoteDataSource
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.util.Extension.toArrayList

class SearchRepository(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherDao
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

    fun getRecentSearch(): ArrayList<WeatherResponse> {
        return localDataSource.getAll()?.sortedByDescending { it.updatedAt }.toArrayList()
    }

    fun insertOrUpdateRecentSearch(weatherResponse: WeatherResponse) {
        weatherResponse.apply {
            updatedAt = System.currentTimeMillis()
        }
        localDataSource.insertIgnore(weatherResponse)
        localDataSource.updateUpdateAt(weatherResponse.id!!, weatherResponse.updatedAt!!)
    }

    fun getLastSearch(): WeatherResponse? {
        return localDataSource.getLastSearch()
    }
}