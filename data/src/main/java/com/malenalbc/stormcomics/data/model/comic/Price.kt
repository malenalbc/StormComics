package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Price(
    @SerializedName("type") var type: String? = null,
    @SerializedName("price") var price: Double = 0.toDouble()
) : Serializable
