package com.ewake.myfinance.data.repository.category

import android.content.Context
import com.ewake.myfinance.R
import com.ewake.myfinance.data.database.AppDatabase
import com.ewake.myfinance.data.database.entity.CategoryEntity
import com.ewake.myfinance.data.database.mapper.CategoryDatabaseMapper
import com.ewake.myfinance.ui.model.CategoryModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class CategoryRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val context: Context,
    private val mapper: CategoryDatabaseMapper
) : CategoryRepository {

    private val categoryDao = appDatabase.categoryDao()

    override fun getAll(): Single<List<CategoryModel>> =
        Single.fromCallable { categoryDao.getAll() }.map { mapper.entityListToModelList(it) }

    override fun addCategory(model: CategoryModel) {
        categoryDao.insert(mapper.modelToEntity(model))
    }

    override fun checkCategoriesExists(): Single<Boolean> =
        Single.fromCallable { categoryDao.getAll().isEmpty() }

    override fun initCategories() {
        val list = listOf(
            CategoryEntity(id = "0", name = "Еда"),
            CategoryEntity(id = "1", name = "Кафе"),
            CategoryEntity(id = "2", name = "Здоровье"),
            CategoryEntity(id = "3", name = "Транспорт"),
            CategoryEntity(id = "4", name = "Подарки")
        )

        categoryDao.insert(list)
    }
}