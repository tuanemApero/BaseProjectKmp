package com.apero.aiservicekmp.data.model

data class ApiConfig(
    val baseUrl: String,
    val requestTimeout: Long = 15000L,
    val connectTimeout: Long = 15000L,
    val socketTimeout: Long = 15000L,
)