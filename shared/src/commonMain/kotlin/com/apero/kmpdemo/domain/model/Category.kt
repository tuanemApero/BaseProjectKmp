package com.apero.kmpdemo.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a category of video styles.
 *
 * @property id Unique identifier for the category
 * @property name Display name of the category
 * @property data List of style items contained in this category
 */
@Serializable
data class Category (
    val id: String,
    val name: String,
    val data: List<StyleItem>
) {
    companion object {
        const val TOP_TRENDING = "Top Trending"
    }
}