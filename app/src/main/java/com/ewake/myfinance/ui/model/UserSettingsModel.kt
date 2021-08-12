package com.ewake.myfinance.ui.model

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
data class UserSettingsModel(
    var id: Int = 0,
    var selectedPeriodType: PeriodType = PeriodType.DAY
)
