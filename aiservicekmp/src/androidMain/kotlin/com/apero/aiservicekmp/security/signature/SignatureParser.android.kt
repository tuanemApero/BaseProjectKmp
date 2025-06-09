package com.apero.aiservicekmp.security.signature

import android.util.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import kotlin.random.Random

actual fun SignatureParserImpl(): SignatureParser = SignatureParserAndroidImpl()

private class SignatureParserAndroidImpl :
    SignatureParser {
    override fun parse(
        keyId: String,
        publicKeyPem: String,
        timestamp: Long
    ): Result<SignatureData> = runCatching {
        val sig = encrypt(timestamp, keyId, publicKeyPem)
        SignatureData(sig, keyId, timestamp)
    }

    private fun encrypt(
        timestamp: Long,
        keyId: String,
        pem: String
    ): String {
        val publicKey = loadKey(pem)
            ?: throw IllegalArgumentException("Invalid RSA public key")
        val nonce = Random.nextInt(0, 1_000_000)
        val plain = "$timestamp@@@$keyId@@@$nonce"
        val cipher = Cipher.getInstance("RSA/None/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encrypted = cipher.doFinal(plain.toByteArray())
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }

    private fun loadKey(pem: String): PublicKey? {
        val clean = pem
            .replace(PEM_HEADER, "")
            .replace(PEM_FOOTER, "")
            .replace("\\s".toRegex(), "")
        val bytes = Base64.decode(clean, Base64.NO_WRAP)
        return try {
            KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(bytes))
        } catch (_: Exception) {
            null
        }
    }

    private companion object {
        const val PEM_HEADER = "-----BEGIN PUBLIC KEY-----"
        const val PEM_FOOTER = "-----END PUBLIC KEY-----"
    }
}