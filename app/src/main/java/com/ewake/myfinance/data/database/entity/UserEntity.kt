package com.ewake.myfinance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "userEntity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "income")
    var income: Int,
    @ColumnInfo(name = "outcome")
    var outcome: Int,
    @ColumnInfo(name = "balance")
    var balance: Int
)
