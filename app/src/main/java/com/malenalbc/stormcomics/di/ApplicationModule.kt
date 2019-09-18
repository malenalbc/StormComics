package com.malenalbc.stormcomics.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
open class ApplicationModule(
    val applicationContext: Context
) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return applicationContext
    }

}
