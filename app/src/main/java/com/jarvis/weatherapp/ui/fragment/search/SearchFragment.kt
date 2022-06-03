package com.jarvis.weatherapp.ui.fragment.search

import android.content.DialogInterface
import android.view.LayoutInflater
import com.jarvis.weatherapp.base.ui.BaseFragment
import com.jarvis.weatherapp.databinding.FragmentSearchBinding
import com.jarvis.weatherapp.ui.dialog.GeneralDialog
import com.jarvis.weatherapp.viewModel.MainViewModel
import com.jarvis.weatherapp.viewModel.search.SearchViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        
    }

    override fun initListener() {
        
    }

    override fun initStartEvent() {
        
    }

    // TODO i18n
    fun showSearchErrorDialog() {
        GeneralDialog().apply {
            title = "Search Error Dialog"
            message = "Please enter valid zip code/city name !"
            positiveButtonText = "OK"
            positiveButtonCallback = DialogInterface.OnClickListener { dialog, position ->
                dismiss()
            }
        }
    }
}