package com.malenalbc.stormcomics.model

import android.os.Parcel
import android.os.Parcelable
import com.malenalbc.stormcomics.core.extension.getThumbnailUrl
import com.malenalbc.stormcomics.data.model.comic.Comic

data class ComicEntity(
    val id: Int = 0,
    val title: String?,
    val issueNumber: Int = 0,
    var thumbnailUrl: String?,
    val description: String?,
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    constructor(comic: Comic) : this(
        comic.id,
        comic.title,
        comic.issueNumber,
        comic.thumbnail.getThumbnailUrl("portrait_medium"),
        comic.description,
        null
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(issueNumber)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(description)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicEntity> {
        override fun createFromParcel(parcel: Parcel): ComicEntity {
            return ComicEntity(parcel)
        }

        override fun newArray(size: Int): Array<ComicEntity?> {
            return arrayOfNulls(size)
        }
    }
}
