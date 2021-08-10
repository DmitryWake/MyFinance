package com.ewake.myfinance.ui.fragment.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.splashscreen.interactor.SplashScreenInteractor
import com.ewake.myfinance.ui.model.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
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
            .doAfterTerminate {
                _navigateLiveData.postValue(
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToMainPageFragment()
                )
            }
            .doOnComplete { createUser() }
            .subscribe()
            .disposeOnCleared()
    }

    private fun createUser() {
        Single.fromCallable { loader.createUser(UserModel()) }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .disposeOnCleared()
    }
}