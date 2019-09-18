package com.malenalbc.stormcomics.data.model.character

import com.google.gson.annotations.SerializedName


class Image(
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null
) {
    val url: String
        get() = "$path.$extension"
}
