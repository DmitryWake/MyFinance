package com.ewake.myfinance.di

import com.ewake.myfinance.data.database.mapper.CategoryDatabaseMapper
import com.ewake.myfinance.data.database.mapper.TransactionDatabaseMapper
import com.ewake.myfinance.data.database.mapper.UserDatabaseMapper
import dagger.Module
import dagger.Provides

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
class MappersModule {
    @Provides
    fun provideUserDatabaseMapper(): UserDatabaseMapper = UserDatabaseMapper.Companion

    @Provides
    fun provideTransactionDatabaseMapper(): TransactionDatabaseMapper =
        TransactionDatabaseMapper.Companion

    @Provides
    fun provideCategoryDatabaseMapper(): CategoryDatabaseMapper = CategoryDatabaseMapper.Companion
}