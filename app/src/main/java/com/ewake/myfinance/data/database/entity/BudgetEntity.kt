package com.ewake.myfinance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ewake.myfinance.ui.model.TransactionModel
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "budgetEntity")
data class BudgetEntity(
    @PrimaryKey
    var date: Date,
    @ColumnInfo(name = "categoriesOutcome")
    var transactions: MutableList<TransactionModel>,
    @ColumnInfo(name = "transferBalance")
    var transferBalance: Int = 0
)
