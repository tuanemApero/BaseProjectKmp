package com.apero.aiservicekmp.data.datasource.impl

import com.apero.aiservicekmp.data.datasource.GenerateVideoRemoteDatasource
import com.apero.aiservicekmp.data.model.provideHttpClient
import com.apero.aiservicekmp.data.model.request.aivideo.GenerateVideoRequestDto
import com.apero.aiservicekmp.data.model.response.PreSignedLinkData
import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.response.aivideo.GenerateDataDto
import com.apero.aiservicekmp.data.model.response.aivideo.VideoGenerateDto
import com.apero.aiservicekmp.utils.error.ResponseCodeError.GET_PRESIGNED_LINK_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseCodeError.TIME_OUT_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseCodeError.UNKNOWN_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseCodeError.UPLOAD_IMAGE_TO_S3_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseMessageError.TIME_OUT_ERROR_MESSAGE
import com.apero.aiservicekmp.utils.ext.toResponseState
import com.apero.aiservicekmp.utils.ext.withCatching
import com.apero.aiservicekmp.data.model.ApiConfig
import com.apero.aiservicekmp.data.model.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class GenerateVideoRemoteDataSourceImpl(
    private val client: HttpClient,
) : GenerateVideoRemoteDatasource {
    override suspend fun generateVideo(generateVideoRequestDto: GenerateVideoRequestDto): AiServiceResponseState<GenerateDataDto> =
        withCatching {
            client.post("/api/v5/image-2-video") {
                contentType(ContentType.Application.Json)
                setBody(generateVideoRequestDto)
            }.body<ApiResponse<GenerateDataDto>>()
        }.mapCatching { it.toResponseState() }
            .getOrElse {
                AiServiceResponseState.Error(UNKNOWN_ERROR_CODE, it.message)
            }

    override suspend fun getGenerateVideoInfo(generateId: String): AiServiceResponseState<VideoGenerateDto> =
        withCatching {
            client.get("/api/v5/image-2-video/video/$generateId") {
                contentType(ContentType.Application.Json)
            }.body<ApiResponse<VideoGenerateDto>>()
        }.mapCatching { it.toResponseState() }
            .getOrElse {
                AiServiceResponseState.Error(UNKNOWN_ERROR_CODE, it.message)
            }

    override suspend fun getPreSignedLink(): AiServiceResponseState<PreSignedLinkData> =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MILLIS) {
                try {
                    val body = client.get("/api/v5/image-2-video/presigned-link").body<ApiResponse<PreSignedLinkData>>()
                    if (body.data != null) {
                        AiServiceResponseState.Success(body.data)
                    } else {
                        AiServiceResponseState.Error(GET_PRESIGNED_LINK_ERROR_CODE, "Get presigned link null")
                    }
                } catch (e: Exception) {
                    AiServiceResponseState.Error(GET_PRESIGNED_LINK_ERROR_CODE, e.message ?: "Error")
                }
            } ?: AiServiceResponseState.Error(TIME_OUT_ERROR_CODE, TIME_OUT_ERROR_MESSAGE)
        }

    override suspend fun uploadImageToServer(
        preSignedLink: String,
        image: ByteArray,
        contentType: ContentType,
    ): AiServiceResponseState<Boolean> =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MILLIS) {
                val clientS3 = provideHttpClient(
                    apiConfig = ApiConfig(
                        baseUrl = preSignedLink
                    )
                )
                val response = clientS3.put(preSignedLink) {
                    headers {
                        append(HttpHeaders.ContentType, contentType.toString())
                    }
                    setBody(image)
                }

                if (!response.status.isSuccess()) {
                    AiServiceResponseState.Error(
                        UPLOAD_IMAGE_TO_S3_ERROR_CODE,
                        "Upload failed with status: ${response.status}"
                    )
                }
                AiServiceResponseState.Success(true)
            } ?: AiServiceResponseState.Error(TIME_OUT_ERROR_CODE, TIME_OUT_ERROR_MESSAGE)
        }

    companion object {
        const val TIMEOUT_MILLIS = 30000L
    }
}