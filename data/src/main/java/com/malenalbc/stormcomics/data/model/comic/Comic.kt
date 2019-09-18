package com.malenalbc.stormcomics.data.model.comic

import com.google.gson.annotations.SerializedName
import com.malenalbc.stormcomics.data.model.BaseResult
import com.malenalbc.stormcomics.data.model.character.Image
import com.malenalbc.stormcomics.data.model.character.Url
import java.io.Serializable


class Comic (
    @SerializedName("digitalId")
    var digitalId: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("issueNumber")
    var issueNumber: Int = 0,
    @SerializedName("variantDescription")
    var variantDescription: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("modified")
    var modified: String? = null,
    @SerializedName("isbn")
    var isbn: String? = null,
    @SerializedName("upc")
    var upc: String? = null,
    @SerializedName("diamondCode")
    var diamondCode: String? = null,
    @SerializedName("ean")
    var ean: String? = null,
    @SerializedName("issn")
    var issn: String? = null,
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("pageCount")
    var pageCount: Int = 0,
    @SerializedName("textObjects")
    var textObjects: List<TextObject> = ArrayList(),
    @SerializedName("resourceURI")
    var resourceURI: String? = null,
    @SerializedName("urls")
    var urls: List<Url>? = ArrayList(),
    @SerializedName("dates")
    var dates: List<Date>? = ArrayList(),
    @SerializedName("prices")
    var prices: List<Price> = ArrayList(),
    @SerializedName("thumbnail")
    var thumbnail: Image? = null,
    @SerializedName("images")
    var images: List<Image>? = ArrayList(),
    @SerializedName("creators")
    var creators: Creators? = null

): BaseResult(), Serializable
