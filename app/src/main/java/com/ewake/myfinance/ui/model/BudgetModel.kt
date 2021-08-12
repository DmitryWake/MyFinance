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
        get() = transactions.sumOf { it.summ } + transferBalance
    val income: Int
        get() = transactions.sumOf { if (it.summ > 0) it.summ else 0 }
    val outcome: Int
        get() = transactions.sumOf { if (it.summ < 0) it.summ else 0 }.absoluteValue
}
