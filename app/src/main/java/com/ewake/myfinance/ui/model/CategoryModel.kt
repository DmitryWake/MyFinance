package com.ewake.myfinance.ui.model

import androidx.annotation.DrawableRes

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class CategoryModel(
    var name: String = "",
    var limit: Int = 0,
    @DrawableRes
    var iconId: Int? = null
)
