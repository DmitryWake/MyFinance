package com.ewake.myfinance.ui.fragment.mainpage.di

import com.ewake.myfinance.ui.fragment.mainpage.MainPageFragment
import dagger.Subcomponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Subcomponent(modules = [MainPageModule::class])
interface MainPageComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainPageComponent
    }

    fun inject(fragment: MainPageFragment)
}