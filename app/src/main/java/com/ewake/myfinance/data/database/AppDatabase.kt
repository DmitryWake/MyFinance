package com.ewake.myfinance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ewake.myfinance.BuildConfig
import com.ewake.myfinance.data.database.dao.BudgetDao
import com.ewake.myfinance.data.database.dao.UserDao
import com.ewake.myfinance.data.database.entity.BudgetEntity
import com.ewake.myfinance.data.database.entity.UserEntity
import com.ewake.myfinance.data.database.typeconverter.BudgetTypeConverter

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Database(
    entities = [UserEntity::class, BudgetEntity::class],
    version = BuildConfig.DATABASE_VERISON,
    exportSchema = false
)
@TypeConverters(BudgetTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun budgetDao(): BudgetDao
}