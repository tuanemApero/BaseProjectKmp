package com.apero.aiservicekmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val statusCode: Int,
    val message: String?,
    val data: T?,
    val timestamp: Long
)
