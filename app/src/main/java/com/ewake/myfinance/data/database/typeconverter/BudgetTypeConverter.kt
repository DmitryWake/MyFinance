package com.ewake.myfinance.data.database.typeconverter

import androidx.room.TypeConverter
import com.ewake.myfinance.ui.model.CategoryModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class BudgetTypeConverter {

    private val gson = GsonBuilder().create()
    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    @TypeConverter
    fun fromCategoriesOutcome(categoriesOutcome: Map<CategoryModel, Int>): String {
        return gson.toJson(categoriesOutcome)
    }

    @TypeConverter
    fun fromString(categoriesOutcome: String): Map<CategoryModel, Int> {
        return gson.fromJson(
            categoriesOutcome,
            object : TypeToken<Map<CategoryModel, Int>>() {}.type
        )
    }

    @TypeConverter
    fun fromDate(date: Date): String {
        return formatter.format(date)
    }

    @TypeConverter
    fun toDate(date: String): Date {
        return formatter.parse(date)!!
    }

}