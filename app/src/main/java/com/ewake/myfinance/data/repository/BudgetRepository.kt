package com.ewake.myfinance.data.repository

import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.entity.BudgetEntity
import com.ewake.myfinance.data.database.mapper.BudgetMapper
import com.ewake.myfinance.ui.model.BudgetModel
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class BudgetRepository @Inject constructor(
    private val mapper: BudgetMapper,
    appDatabase: AppDatabase
) : Repository() {

    private val budgetDao = appDatabase.budgetDao()

    fun getAll(): Single<List<BudgetModel>> =
        Single.fromCallable { budgetDao.getAll() }.map { mapper.entityListToModelList(it) }

    fun saveBudget(list: List<BudgetModel>) {
        budgetDao.deleteAll()
        budgetDao.insert(mapper.modelListToEntityList(list))
    }

    fun updateBudget(model: BudgetModel) {
        budgetDao.update(mapper.modelToEntity(model))
    }

    fun getDailyBudget(): Single<BudgetModel> {
        return Single.fromCallable { budgetDao.getAll() }
            .map { mapper.entityListToModelList(it) }
            .map {
                val today = Date()
                it.find { model ->
                    model.date.date == today.date &&
                            model.date.month == today.month &&
                            model.date.year == today.year
                }
            }
    }
}