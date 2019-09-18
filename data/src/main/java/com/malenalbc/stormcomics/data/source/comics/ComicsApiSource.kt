package com.malenalbc.stormcomics.data.source.comics

import com.malenalbc.stormcomics.data.core.extension.toResult
import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.model.ResultWrapper
import com.malenalbc.stormcomics.data.model.comic.Comic
import com.malenalbc.stormcomics.data.source.URL_GET_COMICS
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface ComicsApi {
    @GET(URL_GET_COMICS)
    fun getComics(@Query("dateRange") dateRange: String): Single<Response<ResultWrapper<Comic>>>
}

@Singleton
class ComicsApiSource @Inject constructor(
    private val api: ComicsApi
) {
    fun getComics(dateRange: String): Single<Either<Throwable, ArrayList<Comic>>> {
        return api.getComics(dateRange).toResult()
    }

}
