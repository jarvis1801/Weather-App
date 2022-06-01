package com.jarvis.weatherapp.util

import com.jarvis.weatherapp.viewmodel.MainViewModel

object ViewModelCreatorUtil {

    fun buildMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}