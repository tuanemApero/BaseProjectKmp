package com.apero.kmpdemo.data.mapper

import com.apero.aiservicekmp.data.repository.model.aivideo.AiServiceStyleItem
import com.apero.artimind.kmp.core.data.model.AiServiceCategory
import com.apero.kmpdemo.domain.model.Category
import com.apero.kmpdemo.domain.model.StyleItem

fun AiServiceStyleItem.mapToStyleItem(): StyleItem {
    return StyleItem(
        styleId = styleId ?: "",
        orderIndex = orderIndex ?: 0,
        orderIndexInCategory = orderIndexInCategory ?: 0,
        name = name ?: "",
        videoRaw = videoRaw ?: "",
        videoLite = videoLite ?: "",
        thumbnail = thumbnail ?: "",
        introTitle = introTitle ?: "",
        introDescription = introDescription ?: "",
        isShow = isShow,
        category = category ?: "",
        positivePrompt = positivePrompt ?: "",
        positivePrompt2 = positivePrompt2 ?: "",
        positivePrompt3 = positivePrompt3 ?: "",
        negativePrompt = negativePrompt ?: "",
        width = width ?: 512,
        height = height ?: 512,
        mode = mode ?: "default",
        loraName = loraName ?: "",
        segmentId = segmentId,
        imageInput = imageInput ?: 1,
        imageOutput = imageOutput ?: 1,
        subscription = subscription ?: "free",
        metaDataJson = metaDataJson ?: "{}",
        segementLocation = segementLocation ?: "",
    )
}

fun AiServiceCategory.mapToCategory(): Category {
    return Category(
        id = id,
        name = name,
        data = data.map { it.mapToStyleItem() }
    )
}