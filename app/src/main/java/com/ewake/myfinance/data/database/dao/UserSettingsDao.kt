package com.ewake.myfinance.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ewake.myfinance.data.database.base.BaseDao
import com.ewake.myfinance.data.database.entity.UserSettingsEntity
import io.reactivex.rxjava3.core.Maybe

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Dao
interface UserSettingsDao : BaseDao<UserSettingsEntity> {

    @Query("SELECT * FROM userEntity LIMIT 1")
    fun getUser(): Maybe<UserSettingsEntity>

    @Query("DELETE FROM userEntity")
    fun deleteAll()
}