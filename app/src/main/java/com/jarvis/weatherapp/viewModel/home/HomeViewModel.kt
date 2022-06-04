package com.jarvis.weatherapp.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.model.WeatherResponse

class HomeViewModel(
    val homeRepository: HomeRepository
) : BaseViewModel() {

    private var _currentWeather = MutableLiveData<WeatherResponse>()
    var currentWeather = _currentWeather as LiveData<WeatherResponse>

    fun postCurrentWeather(weather: WeatherResponse) {
        _currentWeather.postValue(weather)
    }
}