package com.apero.aiservicekmp.data.model.response.aivideo.styleitem

import com.apero.aiservicekmp.data.repository.model.aivideo.AiServiceStyleItem
import kotlinx.serialization.Serializable

@Serializable
data class StyleItemDto(
    val styleId: String?,
    val orderIndex: Int?,
    val orderIndexInCategory: Int?,
    val name: String?,
    val videoRaw: String?,
    val videoLite: String?,
    val thumbnail: String?,
    val introTitle: String?,
    val introDescription: String?,
    val isShow: Int?,
    val category: String?,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val positivePrompt3: String?,
    val negativePrompt: String?,
    val width: Int?,
    val high: Int?,
    val mode: String?,
    val loraName: String?,
    val segmentId: String?,
    val imageInput: Int?,
    val imageOutput: Int?,
    val subscription: String?,
    val metaDataJson: String?,
    val segementLocation: String?
)

fun StyleItemDto.mapToStyleItem() = AiServiceStyleItem(
    styleId = styleId,
    orderIndex = orderIndex,
    orderIndexInCategory = orderIndexInCategory,
    name = name,
    videoRaw = videoRaw,
    videoLite = videoLite,
    thumbnail = thumbnail,
    introTitle = introTitle,
    introDescription = introDescription,
    isShow = this.isShow == 1,
    category = category,
    positivePrompt = positivePrompt,
    positivePrompt2 = positivePrompt2,
    positivePrompt3 = positivePrompt3,
    negativePrompt = negativePrompt,
    width = width,
    height = high,
    mode = mode,
    loraName = loraName,
    segmentId = segmentId ?: "",
    imageInput = imageInput,
    imageOutput = imageOutput,
    subscription = subscription,
    metaDataJson = metaDataJson,
    segementLocation = segementLocation
)