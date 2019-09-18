package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Creators(
    @SerializedName("available") var available: Int = 0,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var creatorSummaries: List<CreatorSummary> = ArrayList(),
    @SerializedName("returned") var returned: Int = 0
) : Serializable
