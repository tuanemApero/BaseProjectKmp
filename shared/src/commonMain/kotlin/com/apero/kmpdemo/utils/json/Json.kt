package com.apero.kmpdemo.utils.json

import kotlinx.serialization.json.Json

internal val standardJson = Json {
    explicitNulls = false
    encodeDefaults = true
    isLenient = true
    ignoreUnknownKeys = true
}