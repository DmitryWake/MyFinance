package com.ewake.myfinance.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ewake.myfinance.ui.model.PeriodType

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Entity(tableName = "userEntity")
data class UserSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "selectedPeriodType")
    var selectedPeriodType: PeriodType
)
