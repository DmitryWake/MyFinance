package com.ewake.myfinance.di

import android.content.Context
import com.ewake.myfinance.di.viewmodel.ViewModelBuilderModule
import com.ewake.myfinance.ui.fragment.history.di.HistoryComponent
import com.ewake.myfinance.ui.fragment.mainpage.di.MainPageComponent
import com.ewake.myfinance.ui.fragment.settings.di.SettingsComponent
import com.ewake.myfinance.ui.fragment.splashscreen.di.SplashScreenComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,
        MappersModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun context(): Context

    fun mainPageComponent(): MainPageComponent.Factory
    fun splashScreenComponent(): SplashScreenComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
    fun historyComponent(): HistoryComponent.Factory
}