package com.jarvis.weatherapp.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var requestLoading = MutableLiveData<Boolean>()
    var reduceLoading = MutableLiveData<Boolean>()

    var requestError = MutableLiveData<String>()

    fun requestApi() {
        requestLoading.postValue(true)
    }

    fun requestApiFinished() {
        reduceLoading.postValue(true)
    }

    fun showErrorPopup(msg: String) {
        requestError.postValue(msg)
    }
}