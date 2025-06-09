package com.apero.aiservicekmp.utils.ext

import com.apero.aiservicekmp.common.Platform
import com.apero.aiservicekmp.common.current
import com.apero.aiservicekmp.security.signature.SignatureParser
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.datetime.Clock

fun HttpClientConfig<*>.addSignatureHeadersPlugin(
    apiKey: String,
    publicKey: String,
    bundleId: String,
    appName: String
) {
    install("DynamicHeaders") {
        requestPipeline.intercept(HttpRequestPipeline.State) {
            val signatureResult = SignatureParser.parseData(
                apiKey,
                publicKey,
                Clock.System.now().toEpochMilliseconds()
            )
            signatureResult.getOrNull()?.let { signature ->
                context.headers {
                    append(HttpHeaders.Accept, ContentType.Application.Json.toString())
                    append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    append("device", Platform.current.name.lowercase())
                    append("x-api-signature", signature.signature)
                    append("x-api-timestamp", signature.timestamp.toString())
                    append("x-api-keyid", signature.keyId)
                    append("x-api-bundleId", bundleId)
                    append("App-name", appName)
                }
            }
            proceed()
        }
    }
}