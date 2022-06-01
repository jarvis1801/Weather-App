package com.jarvis.weatherapp.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var requestLoading = MutableLiveData<Boolean>()
    var reduceLoading = MutableLiveData<Boolean>()

    fun requestApi() {
        requestLoading.postValue(true)
    }

    fun requestApiFinished() {
        reduceLoading.postValue(true)
    }
}