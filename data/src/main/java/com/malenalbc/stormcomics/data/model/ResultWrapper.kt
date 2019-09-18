package com.malenalbc.stormcomics.data.model

import com.google.gson.annotations.SerializedName


class ResultWrapper<D : BaseResult>(
    @SerializedName("code") var code: Int = 0,
    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("attributionText") var attributionText: String? = null,
    @SerializedName("attributionHTML") var attributionHTML: String? = null,
    @SerializedName("etag") var etag: String? = null,
    @SerializedName("data") var data: ResultContainer<D>? = null
)
