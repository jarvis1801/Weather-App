package com.jarvis.weatherapp.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.jarvis.weatherapp.R
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

    fun showKeyboard(view: View) {
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view.findFocus(), SHOW_IMPLICIT)
    }

    fun getCurrentFragment(): Fragment? {
        val navHostFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.first()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}