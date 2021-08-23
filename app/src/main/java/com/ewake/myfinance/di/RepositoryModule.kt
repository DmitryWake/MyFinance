package com.ewake.myfinance.di

import android.content.Context
import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.mapper.CategoryDatabaseMapper
import com.ewake.myfinance.data.database.mapper.TransactionDatabaseMapper
import com.ewake.myfinance.data.repository.budget.BudgetRepositoryImpl
import com.ewake.myfinance.data.repository.budget.BudgetRepository
import com.ewake.myfinance.data.repository.category.CategoryRepository
import com.ewake.myfinance.data.repository.category.CategoryRepositoryImpl
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

    @Provides
    fun provideCategoryRepository(
        context: Context,
        mapper: CategoryDatabaseMapper,
        appDatabase: AppDatabase
    ): CategoryRepository = CategoryRepositoryImpl(appDatabase, context, mapper)
}