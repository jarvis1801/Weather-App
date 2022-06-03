package com.jarvis.weatherapp.ui.fragment.home

import android.view.LayoutInflater
import com.jarvis.weatherapp.base.ui.BaseFragment
import com.jarvis.weatherapp.databinding.FragmentHomeBinding
import com.jarvis.weatherapp.viewModel.MainViewModel
import com.jarvis.weatherapp.viewModel.home.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getActivityViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        
    }

    override fun initListener() {
        mViewBinding.customSearchView.setGotoSearchPageClick(this)
    }

    override fun initStartEvent() {
        
    }
}