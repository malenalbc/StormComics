package com.malenalbc.stormcomics.data.model.character

import android.os.Parcel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import java.io.Serializable

data class Url(
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null
) : Serializable
