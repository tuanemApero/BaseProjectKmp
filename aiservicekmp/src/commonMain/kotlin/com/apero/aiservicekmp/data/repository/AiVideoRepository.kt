package com.apero.aiservicekmp.data.repository

import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.artimind.kmp.core.data.model.AiServiceCategory

interface AiVideoRepository {
    suspend fun getAllStyles() : AiServiceResponseState<List<AiServiceCategory>>
}