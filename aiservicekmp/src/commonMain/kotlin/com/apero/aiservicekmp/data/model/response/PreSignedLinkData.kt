package com.apero.aiservicekmp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PreSignedLinkData(
    val url: String,
    val path: String,
)