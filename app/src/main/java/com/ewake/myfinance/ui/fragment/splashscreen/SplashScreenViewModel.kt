package com.ewake.myfinance.ui.fragment.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.splashscreen.interactor.SplashScreenInteractor
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.UserSettingsModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenViewModel @Inject constructor(private val loader: SplashScreenInteractor) :
    BaseViewModel() {

    private val _navigateLiveData = MutableLiveData<NavDirections>()
    val navigateLiveData: LiveData<NavDirections>
        get() = _navigateLiveData

    override fun onStart() {
        checkUser()
    }

    private fun checkUser() {
        loader.loadUser().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { createUser() }
            .subscribe({
                _navigateLiveData.postValue(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainPageGraph())
            }, {
                Timber.e(it)
            })
            .disposeOnCleared()
    }

    private fun createUser() {
        Single.fromCallable { loader.createUser(UserSettingsModel()) }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .disposeOnCleared()
    }
}