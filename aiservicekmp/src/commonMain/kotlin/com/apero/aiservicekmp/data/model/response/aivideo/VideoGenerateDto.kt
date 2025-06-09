package com.apero.aiservicekmp.data.model.response.aivideo

import com.apero.aiservicekmp.data.repository.model.aivideo.VideoGenerate
import com.apero.aiservicekmp.data.repository.model.aivideo.VideoStatusResponse
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data class VideoGenerateDto(
    val appName: String,
    val notificationId: String,
    val notificationProvider: String,
    val videoId: String,
    val file: String?,
    val mode: String?,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val negativePrompt: String?,
    val keepFace: Boolean?,
    val result: GenerateResult?
) {
    @OptIn(ExperimentalTime::class)
    fun mapToVideoGenerate() : VideoGenerate {
        return VideoGenerate(
            videoId = this.videoId,
            videoStatusResponse = VideoStatusResponse.fromString(this.result?.videoStatus),
            videoUrl = this.result?.resultFile,
            notificationProvider = this.notificationProvider,
            notificationId = this.notificationId,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            estimatedTime = 0L
        )
    }
}
@Serializable
data class GenerateResult(
    val videoId: String,
    val videoStatus: String,
    val resultFile: String? = null,
    val errorMessage: String? = null
)