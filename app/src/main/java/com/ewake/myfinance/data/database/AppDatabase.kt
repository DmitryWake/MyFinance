package com.ewake.myfinance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewake.myfinance.BuildConfig
import com.ewake.myfinance.data.database.dao.UserDao
import com.ewake.myfinance.data.database.entity.UserEntity

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Database(
    entities = [UserEntity::class],
    version = BuildConfig.DATABASE_VERISON,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}