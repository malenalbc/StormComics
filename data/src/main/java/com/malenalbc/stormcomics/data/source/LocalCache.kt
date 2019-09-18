package com.malenalbc.stormcomics.data.source

import android.content.Context
import com.malenalbc.stormcomics.data.core.functional.Either
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.*
import javax.inject.Inject

open class LocalCache<S : Serializable> constructor(
    private val storageName: String
) {
    @Inject
    lateinit var appContext: Context

    fun cache(serializableObject: S?): Single<Either<Throwable, S>> {
        return Single.defer<Either<Throwable, S>> {
            try {
                val fos = appContext.openFileOutput(storageName, Context.MODE_PRIVATE)
                val oos = ObjectOutputStream(fos)
                oos.writeUnshared(serializableObject)
                oos.close()
                Single.just(Either.Right(serializableObject as S))
            } catch (e: OutOfMemoryError) {
                Single.just(Either.Right(serializableObject as S))
            } catch (e: IOException) {
                Single.just(Either.Left(e as Throwable))
            }
        }.subscribeOn(Schedulers.io())
    }

    /**
     * Default behaviour ignores if error in local cache and goes to fetch remote
     */
    @Suppress("UNCHECKED_CAST")
    fun recoverFromCache(onErrorReturn: S): Single<S> {
        return Single.defer<S> {
            try {
                val fis = appContext.openFileInput(storageName)
                val ois = ObjectInputStream(fis)
                val serializableObject = ois.readUnshared() as S
                ois.close()
                Single.just(serializableObject)
            } catch (e: OutOfMemoryError) {
                Single.just(onErrorReturn)
            } catch (e: IOException) {
                Single.just(onErrorReturn)
            } catch (e: ClassNotFoundException) {
                Single.just(onErrorReturn)
            }
        }.subscribeOn(Schedulers.io())
    }

    fun flushCache(): Single<Boolean> {
        return Single.defer<Boolean> {
            val dir = appContext.filesDir
            val file = File(dir, storageName)
            val fw: FileWriter
            try {
                fw = FileWriter(file)
                fw.write("")
                fw.close()
                Single.just<Boolean>(file.delete())
            } catch (e: IOException) {
                Single.error<Boolean>(e)
            }
        }.subscribeOn(Schedulers.io())
    }
}
