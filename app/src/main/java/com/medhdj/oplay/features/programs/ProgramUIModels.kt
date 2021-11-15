package com.medhdj.oplay.features.programs

import android.os.Parcelable
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes
import com.medhdj.business.programs.Program
import com.medhdj.business.programs.ProgramDetails
import kotlinx.parcelize.Parcelize

sealed class ProgramUIModels {
    @Parcelize
    data class Program(
        val id: String,
        val title: String,
        val subtitle: String,
        val imageUrl: String? = null,
        val fullscreenImageUrl: String? = null,
        val detailLink: String? = null,
        val pitch: String? = null
    ) : ProgramUIModels(), Parcelable

    data class ProgramDetails(
        val program: Program,
        val mediaItem: MediaItem,
        val pitch: String? = null
    ) : ProgramUIModels() {
        companion object {
            fun createDefaultProgramDetails(program: Program) = ProgramDetails(
                program = program,
                mediaItem = createVideoMediaItem(STATIC_VIDEO_URL)
            )
        }
    }
}

internal fun ProgramDetails?.toProgramDetailsUI(program: ProgramUIModels.Program) =
    ProgramUIModels.ProgramDetails(
        program = program,
        mediaItem = createVideoMediaItem(STATIC_VIDEO_URL),
        pitch = this?.pitch
    )

internal fun Program.toProgramUI() =
    ProgramUIModels.Program(
        id = id,
        title = title,
        subtitle = subtitle,
        imageUrl = programImage?.imageUrl,
        fullscreenImageUrl = programImage?.fullScreenImageUrl,
        detailLink = detailLink
    )

private fun createVideoMediaItem(videoUrl: String): MediaItem =
    MediaItem.Builder()
        .setUri(videoUrl)
        .setMimeType(MimeTypes.APPLICATION_MPD)
        .build()

private const val STATIC_VIDEO_URL =
    "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
