package com.apero.kmpdemo.domain.usecase

import com.apero.kmpdemo.domain.model.Category
import com.apero.kmpdemo.domain.repository.StyleItemRepository
import com.apero.kmpdemo.utils.ext.ResponseState

class GetCategoryInHomeUseCase(
    private val styleItemRepository: StyleItemRepository
) {
    suspend operator fun invoke() : ResponseState<List<Category>> {
        return styleItemRepository.getCategory()
    }
} 