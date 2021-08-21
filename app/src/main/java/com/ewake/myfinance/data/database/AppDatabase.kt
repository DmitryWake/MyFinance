package com.ewake.myfinance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ewake.myfinance.BuildConfig
import com.ewake.myfinance.data.database.dao.TransactionDao
import com.ewake.myfinance.data.database.dao.UserSettingsDao
import com.ewake.myfinance.data.database.entity.TransactionEntity
import com.ewake.myfinance.data.database.entity.UserSettingsEntity
import com.ewake.myfinance.data.database.typeconverter.TransactionTypeConverter
import com.ewake.myfinance.data.database.typeconverter.UserSettingsConverter

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Database(
    entities = [UserSettingsEntity::class, TransactionEntity::class],
    version = BuildConfig.DATABASE_VERISON,
    exportSchema = false
)
@TypeConverters(TransactionTypeConverter::class, UserSettingsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserSettingsDao
    abstract fun transactionDao(): TransactionDao
}