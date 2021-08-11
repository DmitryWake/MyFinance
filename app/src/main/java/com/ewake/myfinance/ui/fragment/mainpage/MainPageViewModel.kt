package com.ewake.myfinance.ui.fragment.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.mainpage.interactor.MainPageInteractor
import com.ewake.myfinance.ui.model.BudgetModel
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

    private val _budgetLiveData = MutableLiveData<BudgetModel>()
    val budgetLiveData: LiveData<BudgetModel>
        get() = _budgetLiveData

    private var budgetModel: BudgetModel? = null
        set(value) {
            field = value
            value?.let { _budgetLiveData.postValue(it) }
        }

    override fun onStart() {
        loadData()
    }

    private fun loadData() {
        loader.getDailyBudget()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                budgetModel = it
            }, {
                Timber.e(it)
            }).disposeOnCleared()
    }

    fun onAddButtonClicked() {
        val updatedModel = budgetModel?.copy(
            income = budgetModel!!.income + 1,
            balance = budgetModel!!.income + 1 - budgetModel!!.outcome
        )

        updatedModel?.let { updateBudget(updatedModel) }
    }

    fun onMinusButtonClicked() {
        val updatedModel = budgetModel?.copy(
            outcome = budgetModel!!.outcome + 1,
            balance = budgetModel!!.income - budgetModel!!.outcome - 1
        )

        updatedModel?.let { updateBudget(updatedModel) }
    }

    private fun updateBudget(model: BudgetModel) {
        Single.fromCallable {
            loader.updateBudget(model)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                budgetModel = model
            }, {
                Timber.e(it)
            })
    }
}