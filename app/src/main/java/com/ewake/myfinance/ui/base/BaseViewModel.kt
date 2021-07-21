package com.ewake.myfinance.ui.base

import androidx.lifecycle.ViewModel

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
abstract class BaseViewModel : ViewModel() {

    private var isStart = false

    fun start() {
        if (isStart) {
            onStart()
        }
    }

    protected abstract fun onStart()
}