package com.malenalbc.stormcomics.data.di

import com.malenalbc.stormcomics.data.core.api.MarvelInterceptor
import com.malenalbc.stormcomics.data.core.ioThread
import com.malenalbc.stormcomics.data.source.BASE_URL
import com.malenalbc.stormcomics.data.source.character.CharacterApi
import com.malenalbc.stormcomics.data.source.comics.ComicsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class ApiModule {
    @Provides
    @Singleton
    open fun provideOkHttpClient(marvelInterceptor: MarvelInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(marvelInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    open fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    open fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioThread()))
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComicsApi(retrofit: Retrofit): ComicsApi {
        return retrofit.create(ComicsApi::class.java)
    }
}
