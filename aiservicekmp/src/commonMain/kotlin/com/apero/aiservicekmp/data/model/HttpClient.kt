package com.apero.aiservicekmp.data.model

import com.apero.aiservicekmp.utils.serialization.standardJson
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

expect fun provideHttpClient(apiConfig: ApiConfig, extraConfigs: HttpClientConfig<*>.() -> Unit = {}): HttpClient
internal fun <T: HttpClientEngineConfig> HttpClientConfig<T>.configureClient(apiConfig: ApiConfig) {
    install(ContentNegotiation) {
        json(standardJson)
    }
    install(HttpTimeout) {
        requestTimeoutMillis = apiConfig.requestTimeout
        connectTimeoutMillis = apiConfig.connectTimeout
        socketTimeoutMillis = apiConfig.socketTimeout
    }

    install(DefaultRequest) {
        url(apiConfig.baseUrl)
    }
    install(UserAgent) {
        agent = "ktor-client"
    }
}

val HttpStatusCode.Companion.JsonParseError
    get() = HttpStatusCode(490, "JsonParseError")