package com.ewake.myfinance.ui.model

import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class TransactionModel(
    var id: Long,
    var value: Int,
    var date: Date = Date(),
    var categoryModel: CategoryModel? = null
)
