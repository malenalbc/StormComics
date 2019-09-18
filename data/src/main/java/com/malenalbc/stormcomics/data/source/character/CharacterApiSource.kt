package com.malenalbc.stormcomics.data.source.character

import com.malenalbc.stormcomics.data.core.extension.toSingleResult
import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.model.ResultWrapper
import com.malenalbc.stormcomics.data.model.character.Character
import com.malenalbc.stormcomics.data.source.URL_GET_STORM_CHARACTER
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface CharacterApi {
    @GET(URL_GET_STORM_CHARACTER)
    fun getCharacter(): Single<Response<ResultWrapper<Character>>>
}

@Singleton
class CharacterApiSource @Inject constructor(
    private val api: CharacterApi
) {
    fun getCharacter(): Single<Either<Throwable, Character>> {
        return api.getCharacter().toSingleResult()
    }

}
