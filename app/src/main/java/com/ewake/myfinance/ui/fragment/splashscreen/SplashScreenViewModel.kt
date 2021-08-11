package com.ewake.myfinance.ui.fragment.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.splashscreen.interactor.SplashScreenInteractor
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.UserModel
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
        sync()
    }

    private fun sync() {
        loader.loadBudget().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // TODO Вынести логику в репозиторий и добавить перенос остатка
                val newList = it.toMutableList()
                newList.sortBy { item -> item.date }

                val today = Date()

                if (newList.isEmpty()) {
                    newList.add(BudgetModel())
                } else if (newList.last().date.date != today.date ||
                    newList.last().date.month != today.month ||
                    newList.last().date.year != today.year
                ) {
                    newList.add(BudgetModel())
                }

                saveBudget(newList)

            }, {
                Timber.e(it)
            })
    }

    private fun saveBudget(newList: List<BudgetModel>) {
        Single.fromCallable {
            loader.saveBudget(newList)
        }.subscribeOn(Schedulers.io())
            .subscribe({
                _navigateLiveData.postValue(
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToMainPageFragment()
                )
            }, {
                Timber.e(it)
            })
    }

    private fun checkUser() {
        loader.loadUser().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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