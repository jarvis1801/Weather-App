package com.jarvis.weatherapp.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.jarvis.weatherapp.base.viewModel.BaseViewModel
import com.jarvis.weatherapp.viewModel.ViewModelFactory

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
    private var _viewBinding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val mViewBinding: VB
        get() = _viewBinding as VB

    var mViewModel: VM? = null

    protected abstract fun getViewModelClass(): Class<VM>

    abstract fun initView()
    abstract fun initListener()
    abstract fun initStartEvent()
    open fun subscribeViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()

        initViewModel()
        subscribeViewModel()

        initView()
        initListener()
        initStartEvent()
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory(this, intent.extras)
        mViewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
    }

    private fun initLayout() {
        _viewBinding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_viewBinding).root)
    }


    fun showDialog(dialog: DialogFragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            dialog.show(supportFragmentManager, tag)
        }
    }

    fun getFragmentStack(): Int {
        return supportFragmentManager.backStackEntryCount
    }

//    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(LanguageUtil.createContextForLangChange(base))
//    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}