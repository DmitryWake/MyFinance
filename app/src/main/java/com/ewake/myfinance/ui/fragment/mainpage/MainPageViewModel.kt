package com.ewake.myfinance.ui.fragment.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.mainpage.interactor.MainPageInteractor
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.TransactionModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*
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
        budgetModel?.let {
            val transactionModel = TransactionModel(Date().time, 1)
            val newModel = it.also { it.transactions.add(transactionModel) }
            updateBudget(newModel)
        }
    }

    fun onMinusButtonClicked() {
        budgetModel?.let {
            val transactionModel = TransactionModel(Date().time, -1)
            val newModel = it.also { it.transactions.add(transactionModel) }
            updateBudget(newModel)
        }
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