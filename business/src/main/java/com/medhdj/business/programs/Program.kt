package com.medhdj.business.programs

import com.medhdj.business.common.Picture

data class Program(
    val id: String,
    val title: String,
    val subtitle: String,
    val detailLink: String,
    val programImage: Picture?
)
