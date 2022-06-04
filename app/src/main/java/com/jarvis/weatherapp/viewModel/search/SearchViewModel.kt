package com.jarvis.weatherapp.viewModel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherapp.base.datasource.Status
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.model.WeatherResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val searchRepository: SearchRepository
) : BaseViewModel() {

    var isRequestingWeatherLocation = false
    var isRequestingWeatherSearch = false

    private var _weatherData = MutableLiveData<WeatherResponse?>()
    var weatherData = _weatherData as LiveData<WeatherResponse?>

    fun getWeatherFromLocation(lat: Double, lon: Double) = viewModelScope.launch(IO) {
        if (isRequestingWeatherLocation) return@launch

        isRequestingWeatherLocation = true
        requestApi()

        val response = withContext(IO) { searchRepository.getWeatherFromLocation(lat.toString(), lon.toString()) }
        if (response.status == Status.SUCCESS) {
            val data = response.data
            _weatherData.postValue(data)
        } else if (response.status == Status.ERROR) {
            requestError.postValue(response.message)
        }

        requestApiFinished()
        isRequestingWeatherLocation = false
    }

    fun getWeatherFromSearch(searchQuery: String) = viewModelScope.launch(IO) {
        if (isRequestingWeatherSearch) return@launch

        isRequestingWeatherSearch = true
        requestApi()

        val cityNameRequest = async(IO) { searchRepository.getWeatherFromCityName(searchQuery) }
        val zipCodeRequest = async(IO) { searchRepository.getWeatherFromZipCode(searchQuery) }
        val cityNameResponse = cityNameRequest.await()
        val zipCodeResponse = zipCodeRequest.await()

        if (cityNameResponse.status == Status.SUCCESS || zipCodeResponse.status == Status.SUCCESS) {
            val data = cityNameResponse.data ?: zipCodeResponse.data
            _weatherData.postValue(data)
        } else {
            requestError.postValue(cityNameResponse.message)
        }
        requestApiFinished()
        isRequestingWeatherSearch = false
    }

}