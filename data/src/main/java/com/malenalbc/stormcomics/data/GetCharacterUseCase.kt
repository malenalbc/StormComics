package com.malenalbc.stormcomics.data

import com.malenalbc.stormcomics.data.core.functional.Either
import com.malenalbc.stormcomics.data.model.character.Character
import com.malenalbc.stormcomics.data.source.character.CharacterApiSource
import io.reactivex.Single
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val apiSource: CharacterApiSource
) {
     fun getStormInfo(): Single<Either<Throwable, Character>> {
        return apiSource.getCharacter()
    }
}
