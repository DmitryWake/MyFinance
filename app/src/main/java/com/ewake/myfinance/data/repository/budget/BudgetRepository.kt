package com.ewake.myfinance.data.repository.budget

import com.ewake.myfinance.data.repository.Repository
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.PeriodType
import com.ewake.myfinance.ui.model.TransactionModel
import io.reactivex.rxjava3.core.Single
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
interface BudgetRepository {
    fun getAll(): Single<List<TransactionModel>>
    fun getBudget(date: Date, periodType: PeriodType): Single<BudgetModel>
}