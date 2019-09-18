package com.malenalbc.stormcomics.data.source.comics

import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.model.comic.Comic
import com.malenalbc.stormcomics.data.source.LocalCache
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicsLocalSource @Inject constructor() : LocalCache<ArrayList<Comic>>(storageName = "cache.comics") {

    fun saveComics(list: List<Comic>): Single<Either<Throwable, ArrayList<Comic>>> {
        return cache(ArrayList(list))
    }

    fun getComics(): Single<ArrayList<Comic>> {
        // For testing purposes
//        return Single.just(
//            arrayListOf(
//                ModelA("1", "https://dummyimage.com/600x400/b317b3/ffffff.png", 123, emptyList()),
//                ModelA("2", "https://dummyimage.com/600x400/b317b3/ffffff.png", 123, emptyList()),
//                ModelA("3", "https://dummyimage.com/600x400/b317b3/ffffff.png", 123, emptyList()),
//                ModelA("4", "https://dummyimage.com/600x400/b317b3/ffffff.png", 123, emptyList())
//            )
//        ).delay(4, TimeUnit.SECONDS)
         return recoverFromCache(onErrorReturn = arrayListOf())
    }

}
