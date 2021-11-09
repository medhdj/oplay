package com.medhdj.core.extension

import android.util.Patterns
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

fun ImageView.setImage(
    imageUrl: String,
    @DrawableRes
    placeholderDrawable: Int? = null,
    @DrawableRes
    errorDrawable: Int? = null,
    circleCrop: Boolean = false
) {
    val isUrlCorrect = Patterns.WEB_URL.matcher(imageUrl).matches()
    if (isUrlCorrect) {
        val requestOptions = RequestOptions().apply {
            placeholderDrawable?.let { drawable -> this.placeholder(drawable) }
            errorDrawable?.let { drawable -> this.error(drawable) }
        }
        runCatching {
            val glideBuilder = Glide.with(this).load(imageUrl)
            if (circleCrop) {
                glideBuilder.circleCrop()
            }

            glideBuilder.apply(requestOptions)
            glideBuilder.into(this)
        }.onFailure {
            Timber.e(it, "Error while loading image: ")
        }
    } else {
        setImageResource(errorDrawable ?: placeholderDrawable ?: return)
    }
}
