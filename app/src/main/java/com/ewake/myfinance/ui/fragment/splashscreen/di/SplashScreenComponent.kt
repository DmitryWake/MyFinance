package com.ewake.myfinance.ui.fragment.splashscreen.di

import com.ewake.myfinance.ui.fragment.splashscreen.SplashScreenFragment
import dagger.Subcomponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Subcomponent(modules = [SplashScreenModule::class])
interface SplashScreenComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashScreenComponent
    }

    fun inject(fragment: SplashScreenFragment)
}