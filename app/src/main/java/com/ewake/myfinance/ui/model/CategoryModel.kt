package com.ewake.myfinance.ui.model

import androidx.annotation.DrawableRes

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class CategoryModel(
    var id: String = "",
    var name: String = "",
    var limit: Int? = null,
    @DrawableRes
    var iconId: Int? = null
)
