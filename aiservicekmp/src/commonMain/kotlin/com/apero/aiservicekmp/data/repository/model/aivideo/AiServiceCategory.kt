package com.apero.artimind.kmp.core.data.model

import com.apero.aiservicekmp.data.repository.model.aivideo.AiServiceStyleItem
import kotlinx.serialization.Serializable

/**
 * Represents a category of video styles.
 *
 * @property id Unique identifier for the category
 * @property name Display name of the category
 * @property data List of style items contained in this category
 */
@Serializable
data class AiServiceCategory (
    val id: String,
    val name: String,
    val data: List<AiServiceStyleItem>
) {
    companion object {
        const val TOP_TRENDING = "Top Trending"
    }
}