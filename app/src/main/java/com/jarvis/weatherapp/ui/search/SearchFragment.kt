package com.jarvis.weatherapp.ui.search

import android.content.DialogInterface
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.jarvis.weatherapp.R
import com.jarvis.weatherapp.base.ui.BaseFragment
import com.jarvis.weatherapp.databinding.FragmentSearchBinding
import com.jarvis.weatherapp.model.WeatherResponse
import com.jarvis.weatherapp.model.WeatherResponse.Companion.TYPE_LOCATION
import com.jarvis.weatherapp.ui.dialog.GeneralDialog
import com.jarvis.weatherapp.ui.dialog.GeneralDialog.Companion.TAG_SEARCH_ERROR_DIALOG
import com.jarvis.weatherapp.ui.search.adapter.RecentSearchAdapter
import com.jarvis.weatherapp.util.LocationUtil.getFusedLocationClient
import com.jarvis.weatherapp.util.LocationUtil.getLastKnownLocation
import com.jarvis.weatherapp.viewModel.MainViewModel
import com.jarvis.weatherapp.viewModel.search.SearchViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val recentSearchAdapter = RecentSearchAdapter { weatherResponse, position ->
        if (weatherResponse.type == TYPE_LOCATION) {
            getLastKnownLocation()
        } else {
            weatherResponse.name?.let { mViewModel?.getWeatherFromSearch(it) }
        }
    }

    fun getLastKnownLocation() {
        fusedLocationClient?.let {
            requireActivity().getLastKnownLocation(fusedLocationClient!!) { location ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude

                    mViewModel?.getWeatherFromLocation(lat, lon)
                }
            }
        }
    }


    override fun subscribeViewModel() {
        super.subscribeViewModel()
        mViewModel?.weatherData?.observe(viewLifecycleOwner) { weather ->
            if (weather != null) {
                if (findNavController().previousBackStackEntry?.destination?.id == R.id.homeFragment) {
                    mActivityViewModel?.postWeather(weather)
                    findNavController().navigateUp()
                }
            }
        }

        mViewModel?.recentSearchList?.observe(viewLifecycleOwner) { recentSearchList ->
            if (recentSearchList != null) {
                recentSearchAdapter.updateData(recentSearchList)
            }
        }

        mViewModel?.requestError?.observe(viewLifecycleOwner) { errorMsg ->
            showSearchErrorDialog()
        }
    }

    override fun initView() {
        mViewBinding.tietSearch.apply {
            Handler(Looper.getMainLooper()).postDelayed({
                requestFocusFromTouch()
                showKeyboard(this)
            }, 50)
        }

        fusedLocationClient = requireContext().getFusedLocationClient()

        mViewBinding.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recentSearchAdapter
            setHasFixedSize(true)
        }
        recentSearchAdapter.updateData(
            arrayListOf(WeatherResponse(type = TYPE_LOCATION))
        )
    }

    override fun initListener() {
        mViewBinding.tietSearch.apply {
            setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = mViewBinding.tietSearch.text.toString()
                    mViewModel?.getWeatherFromSearch(query)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    override fun initStartEvent() {
        mViewModel?.getRecentSearch()
    }

    private fun showSearchErrorDialog() {
        GeneralDialog().apply {
            title = "Search Error Dialog"
            message = "Please enter valid zip code/city name !"
            positiveButtonText = "OK"
            positiveButtonCallback = DialogInterface.OnClickListener { dialog, position ->
                dismiss()
            }
        }.showDialog(TAG_SEARCH_ERROR_DIALOG)
    }
}