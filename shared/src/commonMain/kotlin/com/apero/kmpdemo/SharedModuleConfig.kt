package com.apero.kmpdemo

import com.apero.aiservicekmp.AiServiceConfig

object SharedModuleConfig {
    fun initialize() {
        AiServiceConfig.initialize(
            bundleId = "com.apero.kmpdemo",
            apiKey = "your-api-key-here",
            appName = "KMPDemo"
        )
    }
}