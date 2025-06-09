package com.apero.aiservicekmp.data.model

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpClient(apiConfig: ApiConfig, extraConfigs: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp) {
        configureClient(apiConfig)
        extraConfigs.invoke(this as HttpClientConfig<*>)
    }
}