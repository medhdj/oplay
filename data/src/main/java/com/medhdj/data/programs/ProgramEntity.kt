package com.medhdj.data.programs

import com.google.gson.annotations.SerializedName
import com.medhdj.business.common.Picture
import com.medhdj.business.programs.Program
import com.medhdj.data.common.OcsApi

data class ProgramEntity(
    val id: String,
    val title: List<ProgramTitleEntity>,
    val subtitle: String,
    @SerializedName("detaillink")
    val detailLink: String,
    @SerializedName("imageurl")
    val imageUrl: String? = null,
    @SerializedName("fullscreenimageurl")
    val fullScreenImageUrl: String? = null
)

data class ProgramTitleEntity(val value: String)


internal fun ProgramEntity.toProgram() =
    Program(
        id = id,
        title = title[0].value,
        subtitle = subtitle,
        detailLink = detailLink,
        programImage = Picture.createFrom(OcsApi.STATICS_URL, imageUrl, fullScreenImageUrl)
    )

internal fun List<ProgramEntity>.toPrograms() =
    map {
        it.toProgram()
    }
