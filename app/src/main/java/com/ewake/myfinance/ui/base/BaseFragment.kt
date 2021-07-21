package com.ewake.myfinance.ui.base

import androidx.fragment.app.Fragment
import com.ewake.myfinance.App
import com.ewake.myfinance.di.AppComponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
open class BaseFragment : Fragment() {

    val appComponent: AppComponent
        get() = (requireActivity().application as App).appComponent
}