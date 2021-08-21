package com.ewake.myfinance.ui.model

import java.util.*
import kotlin.math.absoluteValue

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class BudgetModel(
    var date: Date = Date(),
    var transactions: MutableList<TransactionModel> = mutableListOf(),
    var transferBalance: Int = 0
) {
    val balance: Int
        get() = transactions.sumOf { it.value } + transferBalance
    val income: Int
        get() = transactions.sumOf { if (it.value > 0) it.value else 0 }
    val outcome: Int
        get() = transactions.sumOf { if (it.value < 0) it.value else 0 }.absoluteValue
}
