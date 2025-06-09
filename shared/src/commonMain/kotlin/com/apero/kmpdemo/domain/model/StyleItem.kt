package com.apero.kmpdemo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StyleItem(
    val styleId: String?,
    val orderIndex: Int?,
    val orderIndexInCategory: Int?,
    val name: String?,
    val videoRaw: String?,
    val videoLite: String?,
    val thumbnail: String?,
    val introTitle: String?,
    val introDescription: String?,
    val isShow: Boolean?,
    val category: String?,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val positivePrompt3: String?,
    val negativePrompt: String?,
    val width: Int?,
    val height: Int?,
    val mode: String?,
    val loraName: String?,
    val segmentId: String,
    val imageInput: Int?,
    val imageOutput: Int?,
    val subscription: String?,
    val metaDataJson: String?,
    val segementLocation: String?
)