package com.apero.kmpdemo.data.local.mapper

import com.apero.aiservicekmp.data.repository.model.aivideo.AiServiceStyleItem
import com.apero.kmpdemo.data.local.entity.StyleItemEntity

fun AiServiceStyleItem.mapToStyleItemEntity(): StyleItemEntity {
    return StyleItemEntity(
        styleId = styleId ?: "",
        orderIndex = orderIndex ?: 0,
        orderIndexInCategory = orderIndexInCategory ?: 0,
        name = name ?: "",
        videoRaw = videoRaw ?: "",
        videoLite = videoLite ?: "",
        thumbnail = thumbnail ?: "",
        introTittle = introTitle ?: "",
        introDescription = introDescription ?: "",
        isShow = isShow ?: false,
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
        segementLocation = segementLocation ?: ""
    )
}
