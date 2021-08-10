package com.ewake.myfinance.ui.fragment.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.mainpage.interactor.MainPageInteractor
import com.ewake.myfinance.ui.model.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class MainPageViewModel @Inject constructor(private val loader: MainPageInteractor) :
    BaseViewModel() {

    private val _userLiveData = MutableLiveData<UserModel>()
    val userLiveData: LiveData<UserModel>
        get() = _userLiveData

    override fun onStart() {
        loadUserData()
    }

    private fun loadUserData() {
        loader.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    _userLiveData.postValue(it)
                }
            }, {
                Timber.e(it)
            }).disposeOnCleared()
    }
}