package com.ewake.myfinance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ewake.myfinance.ui.model.CategoryModel
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "transactionEntity")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "value")
    var value: Int,
    @ColumnInfo(name = "date")
    var date: Date,
    @ColumnInfo(name = "categoryModel")
    var categoryModel: CategoryModel? = null
)