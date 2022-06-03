package com.jarvis.weatherapp.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.jarvis.weatherapp.R
import com.jarvis.weatherapp.databinding.CustomSearchViewBinding
import com.jarvis.weatherapp.util.NavigationUtil.gotoSearchFragment

class CustomSearchView @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    var binding = CustomSearchViewBinding.inflate(LayoutInflater.from(context), this, false)

    private var isEnableClick = true
    private var disableClickCallback = {}
        
    init {
        initAttr()
        inflate(getContext(), R.layout.custom_search_view, this)
    }

    private fun initAttr() {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomSearchView, 0, 0).apply {
            try {
                isEnableClick = getBoolean(R.styleable.CustomSearchView_isEnableClick, true)
            } finally {
                recycle()
            }
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!isEnableClick) {
            disableClickCallback()
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun setGotoSearchPageClick(fragment: Fragment) {
        disableClickCallback = { fragment.gotoSearchFragment() }
    }
}