package com.ewake.myfinance.ui.model

import java.util.*

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class BudgetModel(
    var income: Int = 0,
    var outcome: Int = 0,
    var balance: Int = 0,
    var date: Date = Date(),
    var categoriesOutcome: Map<CategoryModel, Int> = mapOf()
)
