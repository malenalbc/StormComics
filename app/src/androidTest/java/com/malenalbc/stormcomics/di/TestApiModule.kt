package com.malenalbc.stormcomics.di

import com.malenalbc.stormcomics.data.core.ioThread
import com.malenalbc.stormcomics.data.di.ApiModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestApiModule : ApiModule() {

    override fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioThread()))
            .build()
    }

    @Singleton
    @Provides
    fun provideMockWebServer(): MockWebServer = MockWebServer()

}
