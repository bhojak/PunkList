package uk.co.bhojak.punkapi

import android.app.Application
import uk.co.bhojak.punkapi.di.AppComponent
import uk.co.bhojak.punkapi.di.AppModule
import uk.co.bhojak.punkapi.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: App): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

}