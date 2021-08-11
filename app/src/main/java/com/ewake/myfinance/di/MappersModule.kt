package com.ewake.myfinance.di

import com.ewake.myfinance.data.database.mapper.BudgetMapper
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
    fun provideBudgetDatabaseMapper(): BudgetMapper = BudgetMapper.Companion
}