package com.apero.aiservicekmp.data.model.request.aivideo

import kotlinx.serialization.Serializable

@Serializable
data class GenerateVideoRequestDto(
    val notificationProvider: String,
    val notificationId: String,
    val files: List<String>,
    val mode: String,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val positivePrompt3: String?,
    val negativePrompt : String? = null,
    val width : Int = 1024,
    val high : Int = 1024,
    val loraName : String? = null
)
