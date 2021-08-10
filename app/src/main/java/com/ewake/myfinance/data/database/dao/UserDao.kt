package com.ewake.myfinance.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ewake.myfinance.data.database.base.BaseDao
import com.ewake.myfinance.data.database.entity.UserEntity
import io.reactivex.rxjava3.annotations.Nullable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM userEntity LIMIT 1")
    fun getUser(): Maybe<UserEntity>

    @Query("DELETE FROM userEntity")
    fun deleteAll()
}