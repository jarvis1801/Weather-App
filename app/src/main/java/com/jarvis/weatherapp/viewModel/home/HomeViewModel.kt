package com.jarvis.weatherapp.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.viewModel.search.SearchRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel(
    val homeRepository: HomeRepository,
    val searchRepository: SearchRepository
) : BaseViewModel() {

    private var _currentWeather = MutableLiveData<WeatherResponse>()
    var currentWeather = _currentWeather as LiveData<WeatherResponse>

    fun postCurrentWeather(weather: WeatherResponse) {
        _currentWeather.postValue(weather)
    }

    fun getLastSearch() = viewModelScope.launch(IO) {
        val lastSearch = searchRepository.getLastSearch()
        lastSearch?.let {
            _currentWeather.postValue(it)
        }
    }
}