package com.malenalbc.stormcomics.core.extension

import com.malenalbc.stormcomics.core.androidThread
import com.malenalbc.stormcomics.data.core.newThread
import io.reactivex.Flowable
import io.reactivex.Single


fun <T> Flowable<T>.subscribeForUI(): Flowable<T> =
    this.subscribeOn(newThread())
        .observeOn(androidThread())

fun <T> Single<T>.subscribeForUI(): Single<T> =
    this.subscribeOn(newThread())
        .observeOn(androidThread())
