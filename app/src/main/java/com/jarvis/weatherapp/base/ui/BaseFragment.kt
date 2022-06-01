package com.jarvis.weatherapp.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.ui.MainActivity
import com.jarvis.weatherapp.viewmodel.MainViewModel
import com.jarvis.weatherapp.viewmodel.ViewModelFactory

abstract class BaseFragment<VB : ViewBinding, VM: BaseViewModel, AVM: MainViewModel> : Fragment() {
    private var _viewBinding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val mViewBinding: VB
        get() = _viewBinding as VB

    var mViewModel: VM? = null
    var mActivityViewModel: AVM? = null

    abstract fun getViewModelClass(): Class<VM>?
    abstract fun getActivityViewModelClass(): Class<AVM>?
    open fun subscribeViewModel() {
        mViewModel?.requestLoading?.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            }
        }

        mViewModel?.reduceLoading?.observe(viewLifecycleOwner) {
            if (it == true) {
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.showLoading()
    }

    private fun hideLoading() {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.hideLoading()
    }

    open fun getArgs(): Bundle { return Bundle() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initViewModel()
        subscribeViewModel()
        _viewBinding = bindingInflater.invoke(layoutInflater)
        return _viewBinding?.root
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory(this, getArgs())
        getViewModelClass().takeIf { it != null }?.let { clazz -> mViewModel = ViewModelProvider(this, viewModelFactory)[clazz] }
        getActivityViewModelClass().takeIf { it != null }?.let { clazz -> mActivityViewModel = ViewModelProvider(requireActivity())[clazz] }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initStartEvent()
    }

    abstract fun initView()
    abstract fun initListener()
    abstract fun initStartEvent()
}