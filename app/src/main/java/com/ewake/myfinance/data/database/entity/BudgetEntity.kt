package com.ewake.myfinance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ewake.myfinance.ui.model.CategoryModel
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "budgetEntity")
data class BudgetEntity(
    @ColumnInfo(name = "income")
    var income: Int,
    @ColumnInfo(name = "outcome")
    var outcome: Int,
    @ColumnInfo(name = "balance")
    var balance: Int,
    @PrimaryKey
    var date: Date,
    @ColumnInfo(name = "categoriesOutcome")
    var categoriesOutcome: Map<CategoryModel, Int>
)
