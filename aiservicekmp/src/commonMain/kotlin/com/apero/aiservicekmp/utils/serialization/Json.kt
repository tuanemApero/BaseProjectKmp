package com.apero.aiservicekmp.utils.serialization

import kotlinx.serialization.json.Json

internal val standardJson = Json {
    explicitNulls = false
    encodeDefaults = true
    isLenient = true
    ignoreUnknownKeys = true
}