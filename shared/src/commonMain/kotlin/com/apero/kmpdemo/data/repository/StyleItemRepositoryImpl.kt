package com.apero.kmpdemo.data.repository

import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.repository.AiVideoRepository
import com.apero.kmpdemo.data.mapper.mapToCategory
import com.apero.kmpdemo.domain.model.Category
import com.apero.kmpdemo.domain.repository.StyleItemRepository
import com.apero.kmpdemo.utils.ext.ResponseState
import com.apero.kmpdemo.utils.logger.Logger


class StyleItemRepositoryImpl(
    private val aiVideoRepository: AiVideoRepository
) : StyleItemRepository {
    override suspend fun getCategory(): ResponseState<List<Category>> {
        return when (val response = aiVideoRepository.getAllStyles()) {
            is AiServiceResponseState.Success -> {
                Logger.d("data", "${response.data}")
                ResponseState.Success(response.data.map { it.mapToCategory() })
            }

            is AiServiceResponseState.Error -> {
                Logger.d("data", "${response.message}")
                ResponseState.Error(response.code, response.message)
            }

            else -> ResponseState.Default
        }
    }
}