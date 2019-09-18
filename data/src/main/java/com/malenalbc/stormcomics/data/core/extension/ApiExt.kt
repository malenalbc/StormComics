package com.malenalbc.stormcomics.data.core.extension

import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.model.BaseResult
import com.malenalbc.stormcomics.data.model.ResultWrapper
import io.reactivex.Single
import retrofit2.Response

internal fun <T : BaseResult> Single<Response<ResultWrapper<T>>>.toSingleResult(): Single<Either<Throwable, T>> {
    return this.map { response ->
        if (response.isSuccessful) {
            val result = response.body()?.data?.results?.get(0)
            result?.let { Either.Right(result) } ?: Either.Left(IllegalArgumentException())
        } else {
            Either.Left(IllegalStateException() as Throwable)
        }
    }
        .onErrorReturn {
            Either.Left(it)
        }
}

internal fun <T : BaseResult> Single<Response<ResultWrapper<T>>>.toResult(): Single<Either<Throwable, ArrayList<T>>> {
    return this.map { response ->
        if (response.isSuccessful) {
            val result = response.body()?.data?.results
            result?.let { Either.Right(ArrayList(result)) } ?: Either.Left(IllegalArgumentException())
        } else {
            Either.Left(IllegalStateException() as Throwable)
        }
    }
        .onErrorReturn {
            Either.Left(it)
        }
}
