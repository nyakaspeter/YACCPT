package hu.gy4ez8.yaccpt

import android.app.Application
import hu.gy4ez8.yaccpt.ui.UIModule

class App : Application() {
    lateinit var injector: AppComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppComponent.builder().uIModule(UIModule(this)).build()
    }
}