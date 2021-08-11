package com.ewake.myfinance.data.database.mapper

import com.ewake.myfinance.data.database.entity.BudgetEntity
import com.ewake.myfinance.ui.model.BudgetModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Mapper
interface BudgetMapper : BaseDatabaseMapper<BudgetEntity, BudgetModel> {
    companion object : BudgetMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(BudgetMapper::class.java)