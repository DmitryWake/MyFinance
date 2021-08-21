package com.ewake.myfinance.data.database.typeconverter

import androidx.room.TypeConverter
import com.ewake.myfinance.ui.model.CategoryModel
import com.google.gson.GsonBuilder
import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class TransactionTypeConverter {

    private val gson = GsonBuilder().create()

    @TypeConverter
    fun fromCategory(categoryModel: CategoryModel?): String? {
        return categoryModel?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun fromString(category: String?): CategoryModel? {
        return category?.let { gson.fromJson(it, CategoryModel::class.java) }
    }

    @TypeConverter
    fun fromDate(date: Date): String {
        return gson.toJson(date)
    }

    @TypeConverter
    fun toDate(date: String): Date {
        return gson.fromJson(date, Date::class.java)
    }

}