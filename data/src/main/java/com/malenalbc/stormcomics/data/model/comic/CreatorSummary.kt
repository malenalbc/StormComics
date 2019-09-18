package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CreatorSummary(
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("role") var role: String? = null
) : Serializable
