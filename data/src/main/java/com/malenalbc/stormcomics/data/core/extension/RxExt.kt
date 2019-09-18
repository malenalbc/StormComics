package com.malenalbc.stormcomics.data.core.extension

import android.util.Log
import com.malenalbc.stormcomics.data.BuildConfig
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addToDisposables(disposables: CompositeDisposable?) = disposables?.add(this)

/**
 * Prints the values on next, error, subscription and completion in debug mode
 */
fun <T> Flowable<T>.logLifecycle(name: String = this.toString()): Flowable<T> {
    if (!BuildConfig.DEBUG) return this

    return this
        .doOnNext { Log.i("***RxLifecycle", "$name [Next]: ${it.toString()}") }
        .doOnError { Log.i("***RxLifecycle", "$name [Error]: ${it.message}") }
        .doOnTerminate { Log.i("***RxLifecycle", "$name [Terminated]") }
        .doOnSubscribe { Log.i("***RxLifecycle", "$name [Subscribed]") }
}
