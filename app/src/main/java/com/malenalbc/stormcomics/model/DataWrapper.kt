package com.malenalbc.stormcomics.model

/**
 * Because it's common to load data from the network while showing the disk copy of that data, it's good to create a
 * helper class that you can reuse in multiple places.
 * @see https://developer.android.com/jetpack/docs/guide#addendum
 */
sealed class DataWrapper<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : DataWrapper<T>(data)
    class Empty<T> : DataWrapper<T>()
    class Loading<T>(data: T? = null) : DataWrapper<T>(data)
    class Error<T>(error: Throwable?, data: T? = null) : DataWrapper<T>(data, error)
}
