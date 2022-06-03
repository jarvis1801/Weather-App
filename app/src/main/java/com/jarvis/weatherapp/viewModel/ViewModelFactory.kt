package com.jarvis.weatherapp.viewModel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.jarvis.weatherapp.util.ViewModelCreatorUtil
import com.jarvis.weatherapp.viewModel.home.HomeViewModel
import com.jarvis.weatherapp.viewModel.search.SearchViewModel

class ViewModelFactory(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = Bundle()) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return with(modelClass) {

            when {
                isAssignableFrom(MainViewModel::class.java) -> ViewModelCreatorUtil.buildMainViewModel()

                isAssignableFrom(HomeViewModel::class.java) -> ViewModelCreatorUtil.buildHomeViewModel()

                isAssignableFrom(SearchViewModel::class.java) -> ViewModelCreatorUtil.buildSearchViewModel()

                else ->
                    throw IllegalArgumentException(
                        "Unknown ViewModel class: ${modelClass.name}"
                    )
            }

        } as T
    }

}