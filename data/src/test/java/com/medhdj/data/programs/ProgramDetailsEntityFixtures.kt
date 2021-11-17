package com.medhdj.data.programs

object ProgramDetailsEntityFixtures {

    class Builder(val pitch: String = "this is a pitch") {
        fun build() = ProgramDetailsEntity(pitch = pitch)
    }
}
