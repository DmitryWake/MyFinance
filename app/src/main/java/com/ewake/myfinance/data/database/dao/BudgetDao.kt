package com.ewake.myfinance.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.ewake.myfinance.data.database.base.BaseDao
import com.ewake.myfinance.data.database.entity.BudgetEntity
import io.reactivex.rxjava3.core.Single

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Dao
interface BudgetDao: BaseDao<BudgetEntity> {

    @Query("SELECT * FROM budgetEntity")
    fun getAll(): List<BudgetEntity>

    @Query("DELETE FROM budgetEntity")
    fun deleteAll()
}