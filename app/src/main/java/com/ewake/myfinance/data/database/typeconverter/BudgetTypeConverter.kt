package com.ewake.myfinance.data.database.typeconverter

import androidx.room.TypeConverter
import com.ewake.myfinance.ui.model.CategoryModel
import com.ewake.myfinance.ui.model.TransactionModel
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
    fun fromCategoriesOutcome(transactions: List<TransactionModel>): String {
        return gson.toJson(transactions)
    }

    @TypeConverter
    fun fromString(transactions: String): List<TransactionModel> {
        return gson.fromJson(
            transactions,
            object : TypeToken<List<TransactionModel>>() {}.type
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