package com.malenalbc.stormcomics

import com.malenalbc.stormcomics.di.ApplicationComponent
import com.malenalbc.stormcomics.di.DaggerApplicationComponent
import com.malenalbc.stormcomics.di.TestApiModule
import com.malenalbc.stormcomics.di.TestApplicationModule


/**
 * @see https://medium.com/mindorks/instrumentation-testing-with-mockwebserver-and-dagger2-56778477f0cf
 * @see https://android.jlelse.eu/instrumentation-testing-with-dagger2-retrofit2-mockwebserver-android-4cca7cb7373c
 */
class TestApp : App() {

    override fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(TestApplicationModule(this))
            .apiModule(TestApiModule())
            .build()
    }
}
