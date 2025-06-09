package com.apero.aiservicekmp.data.model.response.aivideo

import com.apero.aiservicekmp.data.repository.model.aivideo.VideoGenerate
import kotlinx.serialization.Serializable
import kotlin.time.Clock.System
import kotlin.time.ExperimentalTime

@Serializable
data class GenerateDataDto(
    val appName: String?,
    val notificationId: String,
    val notificationProvider: String?,
    val videoId: String,
    val file: String?,
    val mode: String?,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val positivePrompt3: String?,
    val frameNumber: Int?,
    val frameRate: Int?,
    val width: Int?,
    val height: Int?,
    val guidanceScale: Int?,
    val steps: Int?,
    val imageSize: Int?,
    val useImageCaption: Boolean?,
    val useFrameInterpolation: Boolean?,
    val enableSwapface: Boolean?,
    val enableInpaint: Boolean?,
    val upscalerXTimes: Int?,
    val enableAssistant: Boolean?,
    val seed: Long?,
    val keepFace: Boolean?
) {
    @OptIn(ExperimentalTime::class)
    fun mapToVideoGenerate() : VideoGenerate {
        return VideoGenerate (
            videoId = this.videoId,
            notificationProvider = this.notificationProvider ?: "",
            notificationId = this.notificationId,
            timestamp = System.now().toEpochMilliseconds(),
            estimatedTime = upscalerXTimes?.toLong() ?: 0L
        )
    }
}