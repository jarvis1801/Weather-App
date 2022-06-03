package com.jarvis.weatherapp.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jarvis.weatherapp.R

object NavigationUtil {

    fun Fragment.gotoSearchFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }

}