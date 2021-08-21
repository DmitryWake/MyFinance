package com.ewake.myfinance.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ewake.myfinance.data.database.base.BaseDao
import com.ewake.myfinance.data.database.entity.TransactionEntity

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM transactionEntity")
    fun getAll(): List<TransactionEntity>
}