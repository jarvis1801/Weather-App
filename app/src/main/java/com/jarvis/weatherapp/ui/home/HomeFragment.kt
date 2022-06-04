package com.jarvis.weatherapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.jarvis.weatherapp.base.ui.BaseFragment
import com.jarvis.weatherapp.databinding.FragmentHomeBinding
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.viewModel.MainViewModel
import com.jarvis.weatherapp.viewModel.home.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun subscribeViewModel() {
        super.subscribeViewModel()
        mViewModel?.currentWeather?.observe(viewLifecycleOwner) { weather ->
            updateWeatherInfo(weather)
        }

        mActivityViewModel?.weatherData?.observe(viewLifecycleOwner) { weather ->
            weather?.let { postWeatherValue(weather) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateWeatherInfo(weather: WeatherResponse) {
        mViewBinding.apply {
            tvCityName.text = weather.name
            tvWeather.text = weather.weather?.first()?.main
            tvTemperature.text = weather.main?.temp.toString()
            tvRealFeelLabel.text = weather.main?.feels_like.toString()
            tvHumidity.text = "${weather.main?.humidity}%"
            tvTemperatureMax.text = weather.main?.temp_max.toString()
            tvTemperatureMin.text = weather.main?.temp_min.toString()
        }

        isShowContentContainer(true)
    }

    override fun initView() {
        isShowContentContainer(false)
    }

    private fun isShowContentContainer(isShow: Boolean) {
        mViewBinding.apply {
            clWeatherContent.isVisible = isShow
            tvNotSelected.isVisible = !isShow
        }
    }

    override fun initListener() {
        mViewBinding.customSearchView.setGotoSearchPageClick(this)
    }

    override fun initStartEvent() {
        mViewModel?.getLastSearch()
    }

    private fun postWeatherValue(weather: WeatherResponse) {
        mViewModel?.postCurrentWeather(weather)
    }
}