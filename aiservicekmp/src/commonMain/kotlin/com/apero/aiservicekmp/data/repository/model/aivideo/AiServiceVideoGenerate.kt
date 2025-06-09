package com.apero.aiservicekmp.data.repository.model.aivideo

import kotlinx.serialization.Serializable

@Serializable
data class VideoGenerate(
    val videoId: String,
    val videoStatusResponse: VideoStatusResponse = VideoStatusResponse.QUEUEING,
    val videoUrl: String? = null,
    val notificationProvider: String,
    val notificationId: String,
    val timestamp: Long,
    val estimatedTime: Long,
    val viewed: Boolean = false,
)

data class VideoGenerateSaved(
    val videoData : VideoGenerate,
    val styleIdRoom : Long,
    val imageRequest : String,
    val wasCompleteDownloadOrExit : Boolean
)

@Serializable
enum class VideoStatusResponse(val value: String) {
    QUEUEING("queueing"),
    PROCESSING("processing"),
    COMPLETED("completed"),
    FAILED("failed");
    companion object {
        fun fromString(value: String?): VideoStatusResponse {
            return entries.firstOrNull { it.value == value } ?: FAILED
        }
    }
}