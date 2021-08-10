package com.ewake.myfinance.ui.fragment.splashscreen.di

import androidx.lifecycle.ViewModel
import com.ewake.myfinance.di.annotation.ViewModelKey
import com.ewake.myfinance.ui.fragment.splashscreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Module
abstract class SplashScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindViewModel(viewModel: SplashScreenViewModel): ViewModel
}