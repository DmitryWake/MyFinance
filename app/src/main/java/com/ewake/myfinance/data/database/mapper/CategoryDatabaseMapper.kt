package com.ewake.myfinance.data.database.mapper

import com.ewake.myfinance.data.database.entity.CategoryEntity
import com.ewake.myfinance.ui.model.CategoryModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Mapper
interface CategoryDatabaseMapper : BaseDatabaseMapper<CategoryEntity, CategoryModel> {
    companion object : CategoryDatabaseMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(CategoryDatabaseMapper::class.java)