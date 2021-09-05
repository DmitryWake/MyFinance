package com.ewake.myfinance.ui.fragment.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ewake.myfinance.ui.base.BaseViewModel
import com.ewake.myfinance.ui.fragment.mainpage.interactor.MainPageInteractor
import com.ewake.myfinance.ui.model.*
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

    private val _categoriesLiveData = MutableLiveData<List<CategoryExpensesModel>>()
    val categoriesLiveData: LiveData<List<CategoryExpensesModel>> = _categoriesLiveData

    private var categories: List<CategoryModel> = listOf()

    private var budgetModel: BudgetModel? = null
        set(value) {
            field = value
            value?.let { _budgetLiveData.postValue(it) }
        }

    override fun onStart() {
        loadCategories()
        loadData()
    }

    private fun loadCategories() {
        loader.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categories = it
                }, {
                    Timber.e(it)
                }
            )
    }

    private fun loadData() {
        loader.getBudget(Date(), PeriodType.MONTH)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                processBudget(it)
            }, {
                Timber.e(it)
            }).disposeOnCleared()
    }

    private fun processBudget(budgetModel: BudgetModel) {
        val categoryExpensesList =
            categories.map { CategoryExpensesModel(categoryModel = it) }.toMutableList()
        val categoryTransactions = budgetModel.transactions.groupBy { it.categoryModel?.id }

        categoryTransactions.keys.forEach { key ->
            val value = categoryTransactions[key]!!.sumOf { it.value }

            categoryExpensesList.find { it.categoryModel?.id == key }.let { model ->
                if (model == null) {
                    categoryExpensesList.add(
                        CategoryExpensesModel(
                            value = value
                        )
                    )
                } else {
                    model.value = value
                }
            }
        }


        this.budgetModel = budgetModel
        _categoriesLiveData.postValue(categoryExpensesList)
    }

    fun onAddButtonClicked() {
        val transactionModel = TransactionModel(
            id = Date().time,
            date = Date(),
            value = 1
        )
        saveTransaction(transactionModel)
    }

    fun onMinusButtonClicked() {
        val transactionModel = TransactionModel(
            id = Date().time,
            date = Date(),
            value = -1
        )
        saveTransaction(transactionModel)
    }

    private fun saveTransaction(model: TransactionModel) {
        Single.fromCallable {
            loader.saveTransaction(model)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                budgetModel?.transactions?.add(model)
                budgetModel?.let { processBudget(it) }
            }, {
                Timber.e(it)
            })
    }
}