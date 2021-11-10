package com.medhdj.business.common

data class Picture(
    val imageUrl: String? = null,
    val fullScreenImageUrl: String? = null
) {
    companion object {
        fun createFrom(
            baseurl: String,
            imageUrl: String?,
            fullScreenImageUrl: String?
        ): Picture? = when {
            imageUrl == null && fullScreenImageUrl == null -> {
                null
            }
            else -> {
                Picture(
                    imageUrl = baseurl + imageUrl,
                    fullScreenImageUrl = baseurl + fullScreenImageUrl
                )
            }
        }
    }
}

