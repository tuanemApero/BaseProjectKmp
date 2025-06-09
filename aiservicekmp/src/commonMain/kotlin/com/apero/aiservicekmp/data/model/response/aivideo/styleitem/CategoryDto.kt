package com.apero.aiservicekmp.data.model.response.aivideo.styleitem

import com.apero.artimind.kmp.core.data.model.AiServiceCategory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: String,
    val name: String,
    val data: List<StyleItemDto>
)

fun CategoryDto.mapToCategory() : AiServiceCategory {
    return AiServiceCategory(
        id = id,
        name = name,
        data = data.map { it.mapToStyleItem() }
    )
}