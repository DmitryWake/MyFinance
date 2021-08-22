package com.ewake.myfinance.ui.fragment.settings.di

import androidx.lifecycle.ViewModel
import com.ewake.myfinance.di.annotation.ViewModelKey
import com.ewake.myfinance.ui.fragment.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
abstract class SettingsModule {
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindViewModel(viewModel: SettingsViewModel): ViewModel
}