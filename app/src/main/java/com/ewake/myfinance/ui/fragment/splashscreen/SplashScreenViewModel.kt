package com.ewake.myfinance.ui.fragment.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.base.SingleEventLiveData
import com.ewake.myfinance.ui.fragment.splashscreen.interactor.SplashScreenInteractor
import com.ewake.myfinance.ui.model.UserSettingsModel
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
    val navigateLiveData: LiveData<NavDirections> = _navigateLiveData

    private val _errorLiveData = SingleEventLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    override fun onStart() {
        sync()
    }

    // FIXME Поправить навигацию и синхронизацию
    private fun sync() {
        loader.loadUser().subscribeOn(Schedulers.io())
            .doOnComplete {
                createUser()
                syncCategories()
                _navigateLiveData.postValue(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainPageGraph())
            }
            .subscribe({
                _navigateLiveData.postValue(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainPageGraph())
            }, {
                _errorLiveData.postValue(it.message)
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

    private fun syncCategories() {
        loader.checkCategoriesExists()
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it) loader.initCategories()
            }, {
                _errorLiveData.postValue(it.message)
                Timber.e(it)
            })
    }
}