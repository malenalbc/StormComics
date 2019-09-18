package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class TextObject(
    @SerializedName("type") var type: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("text") var text: String? = null
) : Serializable
