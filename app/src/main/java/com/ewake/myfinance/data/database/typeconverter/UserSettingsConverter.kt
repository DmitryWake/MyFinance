package com.ewake.myfinance.data.database.typeconverter

import androidx.room.TypeConverter
import com.ewake.myfinance.ui.model.PeriodType

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class UserSettingsConverter {

    @TypeConverter
    fun convertTypeToString(type: PeriodType): String {
        return type.name
    }

    @TypeConverter
    fun convertStringToType(string: String): PeriodType {
        return PeriodType.valueOf(string)
    }
}