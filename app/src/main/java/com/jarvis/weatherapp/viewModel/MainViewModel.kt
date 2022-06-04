package com.jarvis.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.model.WeatherResponse

class MainViewModel : BaseViewModel() {

    private var _weatherData = MutableLiveData<WeatherResponse?>()
    var weatherData = _weatherData as LiveData<WeatherResponse?>

    fun postWeather(weather: WeatherResponse) {
        _weatherData.postValue(weather)
    }
}