package com.jarvis.weatherapp.viewModel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherapp.base.datasource.Status
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.model.WeatherResponse.Companion.TYPE_CITY_NAME_OR_ZIP_CODE
import com.jarvis.weatherapp.model.WeatherResponse.Companion.TYPE_LOCATION
import com.jarvis.weatherapp.util.Extension.toArrayList
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

    private var _recentSearchList = MutableLiveData<ArrayList<WeatherResponse>?>()
    var recentSearchList = _recentSearchList as LiveData<ArrayList<WeatherResponse>?>

    fun getWeatherFromLocation(lat: Double, lon: Double) = viewModelScope.launch(IO) {
        if (isRequestingWeatherLocation) return@launch

        isRequestingWeatherLocation = true
        requestApi()

        val response = withContext(IO) { searchRepository.getWeatherFromLocation(lat.toString(), lon.toString()) }
        if (response.status == Status.SUCCESS) {
            val data = response.data
            data?.type = TYPE_CITY_NAME_OR_ZIP_CODE
            _weatherData.postValue(data)
            launch(IO) { data?.let { searchRepository.insertOrUpdateRecentSearch(data) } }
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
            data?.type = TYPE_CITY_NAME_OR_ZIP_CODE
            _weatherData.postValue(data)
            launch(IO) { data?.let { searchRepository.insertOrUpdateRecentSearch(data) } }
        } else {
            requestError.postValue(cityNameResponse.message)
        }
        requestApiFinished()
        isRequestingWeatherSearch = false
    }

    fun getRecentSearch() = viewModelScope.launch(IO) {
        val list = searchRepository.getRecentSearch()
        val resultList = arrayListOf(WeatherResponse(type = TYPE_LOCATION))
        resultList.addAll(list)
        _recentSearchList.postValue(resultList)
    }

    fun deleteRecentSearch(weatherResponse: WeatherResponse) = viewModelScope.launch(IO) {
        searchRepository.deleteRecentSearch(weatherResponse)
    }
}