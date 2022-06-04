package com.jarvis.weatherapp.util

import com.jarvis.weatherapp.App
import com.jarvis.weatherapp.dataSource.WeatherRemoteDataSource
import com.jarvis.weatherapp.viewModel.MainViewModel
import com.jarvis.weatherapp.viewModel.home.HomeRepository
import com.jarvis.weatherapp.viewModel.home.HomeViewModel
import com.jarvis.weatherapp.viewModel.search.SearchRepository
import com.jarvis.weatherapp.viewModel.search.SearchViewModel

object ViewModelCreatorUtil {

    fun buildMainViewModel(): MainViewModel {
        return MainViewModel()
    }

    fun buildHomeViewModel(): HomeViewModel {
        val homeRepository = HomeRepository()
        val searchRepository = SearchRepository(WeatherRemoteDataSource(), App.database.weatherDao())
        return HomeViewModel(homeRepository, searchRepository)
    }

    fun buildSearchViewModel(): SearchViewModel {
        val searchRepository = SearchRepository(WeatherRemoteDataSource(), App.database.weatherDao())
        return SearchViewModel(searchRepository)
    }
}