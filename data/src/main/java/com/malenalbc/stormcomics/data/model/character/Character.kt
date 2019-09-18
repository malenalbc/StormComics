package com.malenalbc.stormcomics.data.model.character

import com.malenalbc.stormcomics.data.model.BaseResult
import java.io.Serializable

data class Character(
    val name: String? = null,
    val description: String? = null,
    val urls: List<Url>? = null,
    val thumbnail: Image? = null
) : BaseResult(), Serializable
