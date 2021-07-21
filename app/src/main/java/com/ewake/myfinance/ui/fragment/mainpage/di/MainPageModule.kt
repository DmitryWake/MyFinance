package com.ewake.myfinance.ui.fragment.mainpage.di

import androidx.lifecycle.ViewModel
import com.ewake.myfinance.di.annotation.ViewModelKey
import com.ewake.myfinance.ui.fragment.mainpage.MainPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
abstract class MainPageModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainPageViewModel::class)
    abstract fun bindViewModel(viewModel: MainPageViewModel): ViewModel
}