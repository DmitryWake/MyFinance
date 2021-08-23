package com.ewake.myfinance.data.database.entity

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "categoryEntity")
data class CategoryEntity(
    @PrimaryKey
    var id: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "limit")
    var limit: Int? = null,
    @ColumnInfo(name = "iconId")
    @DrawableRes
    var iconId: Int? = null
)
