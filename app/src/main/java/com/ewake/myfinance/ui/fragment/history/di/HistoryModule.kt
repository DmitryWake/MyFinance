package com.ewake.myfinance.ui.fragment.history.di

import androidx.lifecycle.ViewModel
import com.ewake.myfinance.di.annotation.ViewModelKey
import com.ewake.myfinance.ui.fragment.history.HistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
abstract class HistoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindViewModel(viewModel: HistoryViewModel): ViewModel
}