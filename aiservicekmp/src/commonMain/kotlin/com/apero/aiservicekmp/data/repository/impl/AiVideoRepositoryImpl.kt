package com.apero.aiservicekmp.data.repository.impl

import com.apero.aiservicekmp.data.datasource.StyleRemoteDatasource
import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.response.aivideo.styleitem.mapToCategory
import com.apero.aiservicekmp.data.repository.AiVideoRepository
import com.apero.artimind.kmp.core.data.model.AiServiceCategory

class AiVideoRepositoryImpl(
    private val styleDatasource: StyleRemoteDatasource,
) : AiVideoRepository {
    override suspend fun getAllStyles(): AiServiceResponseState<List<AiServiceCategory>> =
        when (val response = styleDatasource.getAllStylesWithCategory()) {
            is AiServiceResponseState.Success -> AiServiceResponseState.Success(response.data.map { it.mapToCategory() })
            is AiServiceResponseState.Error -> response
            is AiServiceResponseState.Default -> response
        }
}
