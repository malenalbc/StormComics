package com.malenalbc.stormcomics.di

import com.malenalbc.stormcomics.App
import com.malenalbc.stormcomics.data.di.ApiModule
import com.malenalbc.stormcomics.di.viewmodel.ViewModelModule
import com.malenalbc.stormcomics.presentation.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, ApiModule::class])
interface ApplicationComponent {
    fun inject(application: App)
    fun inject(listActivity: BaseActivity)
}
