package com.medhdj.business.programs

import com.medhdj.business.common.Picture

object ProgramFixtures {

    fun createProgramsList(size: Int) = MutableList(size) { Builder().build() }

    data class Builder(
        val id: String = "id",
        val title: String = "title",
        val subtitle: String = "subtitle",
        val detailLink: String = "detailsLink",
        val programImage: Picture? = null
    ) {
        fun build() = Program(
            id = id,
            title = title,
            subtitle = subtitle,
            detailLink = detailLink,
            programImage = programImage
        )
    }
}
