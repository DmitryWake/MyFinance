package com.ewake.myfinance.ui.fragment.settings.di

import com.ewake.myfinance.ui.fragment.settings.SettingsFragment
import dagger.Subcomponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)
}