package com.malenalbc.stormcomics.di

import com.malenalbc.stormcomics.App
import com.malenalbc.stormcomics.di.viewmodel.ViewModelModule
import com.malenalbc.stormcomics.presentation.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApplicationModule::class, ViewModelModule::class, TestApiModule::class])
interface TestApplicationComponent : ApplicationComponent {

    override fun inject(application: App)
    override fun inject(listActivity: BaseActivity)
}
