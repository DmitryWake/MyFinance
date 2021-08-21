package com.ewake.myfinance.di

import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.mapper.TransactionDatabaseMapper
import com.ewake.myfinance.data.repository.BudgetRepositoryImpl
import com.ewake.myfinance.data.repository.budget.BudgetRepository
import dagger.Module
import dagger.Provides

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
class RepositoryModule {
    @Provides
    fun provideBudgetRepository(
        mapper: TransactionDatabaseMapper,
        appDatabase: AppDatabase
    ): BudgetRepository = BudgetRepositoryImpl(mapper, appDatabase)
}