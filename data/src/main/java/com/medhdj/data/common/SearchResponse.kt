package com.medhdj.data.common

import com.google.gson.annotations.SerializedName


data class SearchResponse<T>(
    val limit: Int,
    val offset: Int,
    val total: Int,
    val next: String? = null,
    val previous: String? = null,
    @SerializedName("contents")
    val data: List<T>
)

