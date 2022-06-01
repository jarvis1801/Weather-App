package com.jarvis.weatherapp.ui

import android.view.LayoutInflater
import com.jarvis.weatherapp.base.ui.BaseActivity
import com.jarvis.weatherapp.databinding.ActivityMainBinding
import com.jarvis.weatherapp.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {}

    override fun initListener() {}

    override fun initStartEvent() {}

    fun showLoading() {
        mViewBinding.loadingFrame.showLoading()
    }

    fun hideLoading() {
        mViewBinding.loadingFrame.hideLoading()
    }
}