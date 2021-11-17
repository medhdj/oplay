package com.medhdj.business.programs

object ProgramDetailsFixtures {

    data class Builder(
        val pitch: String = "pitch"
    ) {
        fun build() = ProgramDetails(pitch = pitch)
    }
}
