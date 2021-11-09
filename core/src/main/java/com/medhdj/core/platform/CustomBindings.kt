package com.medhdj.core.platform

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.BindingAdapter
import com.medhdj.core.extension.setImage
import com.medhdj.core.functionnal.Response

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let { imageView.setImage(it) }
}

@BindingAdapter("html")
fun renderHtml(textView: TextView, html: String?) {
    html?.let {
        textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(it, FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(it)
        }
    }
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(visible: Boolean?) {
    visible?.let {
        visibility = if (it) View.VISIBLE else View.GONE
    }
}
