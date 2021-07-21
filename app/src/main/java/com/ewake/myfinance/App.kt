package com.ewake.myfinance

import android.app.Application
import com.ewake.myfinance.di.AppComponent
import com.ewake.myfinance.di.DaggerAppComponent

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class App: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}