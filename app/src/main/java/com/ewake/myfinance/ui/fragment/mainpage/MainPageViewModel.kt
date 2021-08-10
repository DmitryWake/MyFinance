package com.ewake.myfinance.ui.fragment.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.mainpage.interactor.MainPageInteractor
import com.ewake.myfinance.ui.model.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
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

    private var userModel: UserModel = UserModel()
        set(value) {
            field = value
            _userLiveData.postValue(value)
        }

    override fun onStart() {
        loadUserData()
    }

    private fun loadUserData() {
        loader.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userModel = it
            }, {
                Timber.e(it)
            }).disposeOnCleared()
    }

    fun onAddButtonClicked() {
        val updatedModel = userModel.copy(
            income = userModel.income + 1,
            balance = userModel.income + 1 - userModel.outcome
        )

        updateUser(updatedModel)
    }

    fun onMinusButtonClicked() {
        val updatedModel = userModel.copy(
            outcome = userModel.outcome + 1,
            balance = userModel.income  - userModel.outcome - 1
        )

        updateUser(updatedModel)
    }

    private fun updateUser(model: UserModel) {
        Single.fromCallable {
            loader.updateCurrentUser(model)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userModel = model
            }, {
                Timber.e(it)
            })
    }
}