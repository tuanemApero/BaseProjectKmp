package com.apero.kmpdemo.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apero.kmpdemo.domain.model.StyleItem

@Entity(
    tableName = "style_item_table",
    indices = [Index(value = ["id"], unique = true)]
)
data class StyleItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val styleId: String,
    val orderIndex: Int,
    val orderIndexInCategory: Int,
    val name: String,
    val videoRaw: String?,
    val videoLite: String?,
    val thumbnail: String?,
    val introTittle: String,
    val introDescription: String,
    val isShow: Boolean,
    val category: String,
    val positivePrompt: String?,
    val positivePrompt2: String?,
    val positivePrompt3: String?,
    val negativePrompt: String?,
    val width: Int,
    val height: Int,
    val mode: String,
    val loraName: String?,
    val segmentId: String,
    val imageInput: Int,
    val imageOutput: Int,
    val subscription: String,
    val metaDataJson: String,
    val segementLocation: String?
) {
    fun mapToStyleItem(): StyleItem {
        return StyleItem(
            styleId = styleId,
            orderIndex = orderIndex,
            orderIndexInCategory = orderIndexInCategory,
            name = name,
            videoRaw = videoRaw,
            videoLite = videoLite,
            thumbnail = thumbnail,
            introTitle = introTittle,
            introDescription = introDescription,
            isShow = isShow,
            category = category,
            positivePrompt = positivePrompt,
            positivePrompt2 = positivePrompt2,
            positivePrompt3 = positivePrompt3,
            negativePrompt = negativePrompt,
            width = width,
            height = height,
            mode = mode,
            loraName = loraName,
            segmentId = segmentId,
            imageInput = imageInput,
            imageOutput = imageOutput,
            subscription = subscription,
            metaDataJson = metaDataJson,
            segementLocation = segementLocation,
        )
    }
}