package com.jarvis.weatherapp.ui

import android.view.LayoutInflater
import com.jarvis.weatherapp.base.ui.BaseActivity
import com.jarvis.weatherapp.databinding.ActivityMainBinding
import com.jarvis.weatherapp.ui.search.SearchFragment
import com.jarvis.weatherapp.util.PermissionUtil
import com.jarvis.weatherapp.util.PermissionUtil.REQUEST_CODE_LOCATION
import com.jarvis.weatherapp.util.PermissionUtil.checkPermissionIsGranted
import com.jarvis.weatherapp.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun subscribeViewModel() {
        mViewModel?.requestLoading?.observe(this) {
            if (it == true) {
                showLoading()
            }
        }

        mViewModel?.reduceLoading?.observe(this) {
            if (it == true) {
                hideLoading()
            }
        }
    }

    override fun initView() {}

    override fun initListener() {}

    override fun initStartEvent() {}

    fun showLoading() {
        mViewBinding.loadingFrame.showLoading()
    }

    fun hideLoading() {
        mViewBinding.loadingFrame.hideLoading()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION && checkPermissionIsGranted(permissions.toCollection(ArrayList()))) {
            val currentFragment = getCurrentFragment()
            if (currentFragment is SearchFragment) {

                currentFragment.getLastKnownLocation()
            }
        }
    }
}