package com.ewake.myfinance.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ewake.myfinance.data.database.base.BaseDao
import com.ewake.myfinance.data.database.entity.CategoryEntity

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Dao
interface CategoryDao: BaseDao<CategoryEntity> {

    @Query("SELECT * FROM categoryEntity")
    fun getAll(): List<CategoryEntity>
}