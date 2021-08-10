package com.ewake.myfinance.di

import android.content.Context
import androidx.room.Room
import com.ewake.myfinance.BuildConfig
import com.ewake.myfinance.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DATABASE_FILE_NAME
        ).build()
    }
}