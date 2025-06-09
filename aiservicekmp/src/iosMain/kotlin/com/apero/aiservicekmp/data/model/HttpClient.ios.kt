package com.apero.aiservicekmp.data.model

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClient(apiConfig: ApiConfig, extraConfigs: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        configureClient(apiConfig)
        extraConfigs.invoke(this as HttpClientConfig<*>)
    }
}