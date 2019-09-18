package com.malenalbc.stormcomics.core.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.malenalbc.stormcomics.R
import com.malenalbc.stormcomics.data.model.character.Image
import com.squareup.picasso.Picasso

fun ImageView?.showImage(url: String?, @DrawableRes placeholder: Int = R.drawable.ic_launcher_background) {
    this?.also {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .into(this)
    }
}

fun Image?.getThumbnailUrl(imageVariant: String = "detail"): String? {
    return this?.let { "$path/$imageVariant.$extension" }
}
