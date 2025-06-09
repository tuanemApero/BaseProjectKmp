package com.apero.aiservicekmp.data.datasource

import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.response.aivideo.styleitem.CategoryDto

interface StyleRemoteDatasource {
    suspend fun getAllStylesWithCategory(
        appName: String = "AI_Artimind_IOS_Visionlab",
        sheet: String = "videoStyle"
    ): AiServiceResponseState<List<CategoryDto>>
}