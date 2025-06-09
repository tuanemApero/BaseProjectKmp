package com.apero.aiservicekmp.security.signature

/**
 * Created by Duy Hung on 13/05/2025.
 */
interface SignatureParser {
    fun parse(
        keyId: String,
        publicKeyPem: String,
        timestamp: Long
    ): Result<SignatureData>

    companion object {
        fun parseData(
            keyId: String,
            publicKeyPem: String,
            timestamp: Long
        ): Result<SignatureData> = SignatureParserImpl()
            .parse(keyId, publicKeyPem, timestamp)
    }
}

data class SignatureData(
    val signature: String,
    val keyId: String,
    val timestamp: Long
)

internal expect fun SignatureParserImpl(): SignatureParser
