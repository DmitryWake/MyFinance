package com.ewake.myfinance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ewake.myfinance.BuildConfig
import com.ewake.myfinance.data.database.dao.BudgetDao
import com.ewake.myfinance.data.database.dao.UserSettingsDao
import com.ewake.myfinance.data.database.entity.BudgetEntity
import com.ewake.myfinance.data.database.entity.UserSettingsEntity
import com.ewake.myfinance.data.database.typeconverter.BudgetTypeConverter
import com.ewake.myfinance.data.database.typeconverter.UserSettingsConverter

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Database(
    entities = [UserSettingsEntity::class, BudgetEntity::class],
    version = BuildConfig.DATABASE_VERISON,
    exportSchema = false
)
@TypeConverters(BudgetTypeConverter::class, UserSettingsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserSettingsDao
    abstract fun budgetDao(): BudgetDao
}