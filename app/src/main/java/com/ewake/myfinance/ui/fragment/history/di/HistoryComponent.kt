package com.ewake.myfinance.ui.fragment.history.di

import com.ewake.myfinance.ui.fragment.history.HistoryFragment
import dagger.Subcomponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Subcomponent(modules = [HistoryModule::class])
interface HistoryComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HistoryComponent
    }

    fun inject(fragment: HistoryFragment)
}