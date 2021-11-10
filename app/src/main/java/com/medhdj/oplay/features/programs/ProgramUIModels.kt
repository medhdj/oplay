package com.medhdj.oplay.features.programs

import com.medhdj.business.programs.Program

sealed class ProgramUIModels {
    data class ProgramGridItem(
        val id: String,
        val title: String,
        val subtitle: String,
        val imageUrl: String? = null
    ) : ProgramUIModels()
}

internal fun Program.toProgramGridItem() =
    ProgramUIModels.ProgramGridItem(
        id = id,
        title = title,
        subtitle = subtitle,
        imageUrl = programImage?.imageUrl
    )
