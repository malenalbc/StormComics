package com.malenalbc.stormcomics.data

import com.malenalbc.stormcomics.data.core.extension.multiLet
import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.core.functional.map
import com.malenalbc.stormcomics.data.model.comic.Comic
import com.malenalbc.stormcomics.data.source.comics.ComicsApiSource
import com.malenalbc.stormcomics.data.source.comics.ComicsLocalSource
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetComicsOfCharacterUseCase @Inject constructor(
    private val apiSource: ComicsApiSource,
    private val localSource: ComicsLocalSource
) {

    fun comics(dateFrom: String? = null, dateTo: String? = null)
            : Flowable<Either<Throwable, ArrayList<Comic>>> {
        return Single.concat(
            localSource.getComics()
                .map { Either.Right(it) },
            getRemoteComics(dateFrom, dateTo)
        )
    }

    private fun getRemoteComics(dateFrom: String?, dateTo: String?)
            : Single<Either<Throwable, ArrayList<Comic>>> {
        val dateRange = multiLet(dateFrom, dateTo) { initialDate, finalDate ->
            "$initialDate,$finalDate"
        } ?: "$INITIAL_DATE_FROM,$INITIAL_DATE_TO"
        return apiSource.getComics(dateRange)
            .doOnSuccess { either -> either.map { saveComics(it) } }
    }

    private fun saveComics(list: List<Comic>): Single<Either<Throwable, ArrayList<Comic>>> {
        return localSource.saveComics(ArrayList(list))
    }

    companion object {
        // Placeholder dates (Ones that we know give results). The proper approach would be
        // incremental searching from a starting date.
        const val INITIAL_DATE_FROM = "2012-01-01"
        const val INITIAL_DATE_TO = "2012-12-01"
    }

}
