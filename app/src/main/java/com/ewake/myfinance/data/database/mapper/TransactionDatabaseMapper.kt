package com.ewake.myfinance.data.database.mapper

import com.ewake.myfinance.data.database.entity.TransactionEntity
import com.ewake.myfinance.ui.model.TransactionModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Mapper
interface TransactionDatabaseMapper : BaseDatabaseMapper<TransactionEntity, TransactionModel> {
    companion object : TransactionDatabaseMapper by INSTANCE
}

private val INSTANCE = Mappers.getMapper(TransactionDatabaseMapper::class.java)