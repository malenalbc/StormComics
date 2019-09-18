package com.malenalbc.stormcomics

import android.app.Application
import com.malenalbc.stormcomics.di.ApplicationComponent
import com.malenalbc.stormcomics.di.ApplicationModule
import com.malenalbc.stormcomics.di.DaggerApplicationComponent

/**
 * @test [TestApp]
 */
open class App : Application() {

    val component: ApplicationComponent by lazy {
        initDagger()
    }

    open fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }


}
