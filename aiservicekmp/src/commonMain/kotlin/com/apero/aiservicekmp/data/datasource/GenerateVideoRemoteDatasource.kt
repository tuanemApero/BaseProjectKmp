package com.apero.aiservicekmp.data.datasource

import com.apero.aiservicekmp.data.model.request.aivideo.GenerateVideoRequestDto
import com.apero.aiservicekmp.data.model.response.PreSignedLinkData
import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.response.aivideo.GenerateDataDto
import com.apero.aiservicekmp.data.model.response.aivideo.VideoGenerateDto
import io.ktor.http.ContentType

interface GenerateVideoRemoteDatasource {
    suspend fun generateVideo(
        generateVideoRequestDto: GenerateVideoRequestDto
    ): AiServiceResponseState<GenerateDataDto>

    suspend fun getGenerateVideoInfo(
        generateId: String
    ): AiServiceResponseState<VideoGenerateDto>

    suspend fun getPreSignedLink(): AiServiceResponseState<PreSignedLinkData>

    suspend fun uploadImageToServer(
        preSignedLink: String,
        image: ByteArray,
        contentType: ContentType = ContentType.Image.JPEG
    ): AiServiceResponseState<Boolean>
}