package com.apero.kmpdemo.domain.repository

import com.apero.kmpdemo.domain.model.Category
import com.apero.kmpdemo.utils.ext.ResponseState

interface StyleItemRepository {
    suspend fun getCategory() : ResponseState<List<Category>>
}