package com.ewake.myfinance.data.repository

import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.mapper.TransactionDatabaseMapper
import com.ewake.myfinance.data.repository.budget.BudgetRepository
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.PeriodType
import com.ewake.myfinance.ui.model.TransactionModel
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class BudgetRepositoryImpl @Inject constructor(
    private val mapper: TransactionDatabaseMapper,
    appDatabase: AppDatabase
) : BudgetRepository {

    private val transactionDao = appDatabase.transactionDao()

    fun saveTransaction(model: TransactionModel) {
        transactionDao.insert(mapper.modelToEntity(model))
    }

    override fun getAll(): Single<List<TransactionModel>> {
        return Single.fromCallable { transactionDao.getAll() }
            .map { mapper.entityListToModelList(it) }
    }

    override fun getBudget(date: Date, periodType: PeriodType): Single<BudgetModel> {
        return getAll().map {
            extractBudget(it.toMutableList(), date, periodType)
        }
    }

    private fun extractBudget(
        list: MutableList<TransactionModel>,
        date: Date,
        periodType: PeriodType
    ): BudgetModel {
        val calendarDate = Calendar.getInstance().apply { time = date }

        list.sortBy { it.date }

        val dailyTransaction = when (periodType) {
            PeriodType.DAY -> extractDailyTransactions(list, calendarDate)
            PeriodType.MONTH -> extractMonthTransactions(list, calendarDate)
            PeriodType.YEAR -> extractYearTransactions(list, calendarDate)
            PeriodType.ALL -> list
            PeriodType.WEEK -> extractWeekTransactions(list, calendarDate)
        }

        list.removeAll { dailyTransaction.contains(it) }

        val transfer = list.sumOf { it.value }

        return BudgetModel(
            transactions = dailyTransaction,
            date = Date(),
            transferBalance = transfer
        )
    }

    private fun extractDailyTransactions(
        list: MutableList<TransactionModel>,
        date: Calendar
    ): MutableList<TransactionModel> {
        return list.filter {
            val modelDate = Calendar.getInstance().apply { time = it.date }
            modelDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    modelDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
        }.toMutableList()
    }

    private fun extractWeekTransactions(
        list: MutableList<TransactionModel>,
        date: Calendar
    ): MutableList<TransactionModel> {
        return list.filter {
            val modelDate = Calendar.getInstance().apply { time = it.date }
            modelDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    modelDate.get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR)
        }.toMutableList()
    }

    private fun extractMonthTransactions(
        list: MutableList<TransactionModel>,
        date: Calendar
    ): MutableList<TransactionModel> {
        return list.filter {
            val modelDate = Calendar.getInstance().apply { time = it.date }
            modelDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    modelDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)
        }.toMutableList()
    }

    private fun extractYearTransactions(
        list: MutableList<TransactionModel>,
        date: Calendar
    ): MutableList<TransactionModel> {
        return list.filter {
            val modelDate = Calendar.getInstance().apply { time = it.date }
            modelDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)
        }.toMutableList()
    }
}