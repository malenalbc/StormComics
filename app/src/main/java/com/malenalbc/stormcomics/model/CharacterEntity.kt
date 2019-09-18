package com.malenalbc.stormcomics.model

import com.malenalbc.stormcomics.core.extension.getThumbnailUrl
import com.malenalbc.stormcomics.data.model.character.Character

data class CharacterEntity(
    val name: String,
    val description: String?,
    var thumbnail: String?
) {
    constructor(character: Character) : this(
        character.name ?: "Storm",
        character.description,
        character.thumbnail.getThumbnailUrl("landscape_incredible")
    )
}
