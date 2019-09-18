package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Date(
    @SerializedName("type") var type: String? = null,
    @SerializedName("date") var date: String? = null
) : Serializable
