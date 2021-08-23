package com.ewake.myfinance.data.repository.category

import com.ewake.myfinance.ui.model.CategoryModel
import io.reactivex.rxjava3.core.Single

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
interface CategoryRepository {
    fun getAll(): Single<List<CategoryModel>>
    fun addCategory(model: CategoryModel)
    fun checkCategoriesExists(): Single<Boolean>
    fun initCategories()
}