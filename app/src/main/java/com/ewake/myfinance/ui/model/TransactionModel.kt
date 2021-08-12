package com.ewake.myfinance.ui.model

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class TransactionModel(
    var id: Long,
    var summ: Int,
    var categoryModel: CategoryModel? = null
)
