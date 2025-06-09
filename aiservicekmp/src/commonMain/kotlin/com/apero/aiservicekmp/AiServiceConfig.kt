package com.apero.aiservicekmp

data class AiServiceConfigModel(
    val bundleId: String,
    val apiKey: String,
    val appName: String,
)

object AiServiceConfig {
    internal lateinit var configModel: AiServiceConfigModel

    fun initialize(bundleId: String, apiKey: String, appName: String) {
        this.configModel = AiServiceConfigModel(bundleId, apiKey, appName)
    }
}