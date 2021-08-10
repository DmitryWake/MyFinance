package com.ewake.myfinance.ui.base

import android.util.SparseArray
import androidx.core.util.forEach
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val compositeDisposable by lazy(::CompositeDisposable)
    private val disposableMap = SparseArray<Disposable>()

    private var isStart = false

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        if (!isStart) {
            isStart = true
            onStart()
        }
    }

    protected abstract fun onStart()

    protected fun Disposable.disposeOnCleared(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    protected fun Disposable.disposeOnCleared(actionId: Int): Disposable {
        disposableMap.put(actionId, this)
        return this
    }

    protected fun disposeById(actionId: Int) {
        disposableMap[actionId]?.dispose()
        disposableMap.remove(actionId)
    }

    protected fun isDisposed(actionId: Int): Boolean {
        return disposableMap[actionId]?.isDisposed ?: false
    }

    override fun onCleared() {
        compositeDisposable.clear()
        disposableMap.forEach { _, value -> value.dispose() }
        super.onCleared()
    }
}