package com.medhdj.data.programs

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.medhdj.business.programs.ProgramDetails

@JsonAdapter(ProgramDetailsEntityTypeAdapter::class)
data class ProgramDetailsEntity(
    val pitch: String
)

fun ProgramDetailsEntity.toProgramDetails() =
    ProgramDetails(pitch = pitch)

class ProgramDetailsEntityTypeAdapter : TypeAdapter<ProgramDetailsEntity>() {
    override fun write(out: JsonWriter, value: ProgramDetailsEntity) {
        TODO("Not yet implemented")
    }

    override fun read(jsonReader: JsonReader): ProgramDetailsEntity? {
        var pitch: String? = null
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            when (jsonReader.nextName()) {
                "contents" -> {
                    pitch = readContents(jsonReader)
                }
                else -> {
                    jsonReader.skipValue()
                }
            }
        }
        jsonReader.endObject()

        return pitch?.let {
            ProgramDetailsEntity(pitch = pitch)
        }
    }

    private fun readContents(jsonReader: JsonReader): String? {
        var pitch: String? = null
        jsonReader.beginObject()
        while (jsonReader.hasNext()) when (jsonReader.nextName()) {
            "pitch" -> {
                pitch = jsonReader.nextString()
            }
            "seasons" -> {
                pitch = readFirstSeason(jsonReader)
            }
            else -> {
                jsonReader.skipValue()
            }
        }
        jsonReader.endObject()
        return pitch
    }

    private fun readFirstSeason(jsonReader: JsonReader): String? {
        var pitch: String? = null
        jsonReader.beginArray()

        jsonReader.beginObject()
        while (jsonReader.hasNext()) when (jsonReader.nextName()) {
            "pitch" -> {
                pitch = jsonReader.nextString()
            }
            else -> {
                jsonReader.skipValue()
            }
        }
        jsonReader.endObject()

        // skip the other seasons
        while (jsonReader.hasNext()) {
            jsonReader.skipValue()
        }

        jsonReader.endArray()

        return pitch
    }
}
