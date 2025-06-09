package com.apero.aiservicekmp.utils.ext

import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.ApiResponse

fun <T> ApiResponse<T>.toResponseState(): AiServiceResponseState<T> {
    return if (statusCode in 200..299 && data != null) {
        AiServiceResponseState.Success(data)
    } else {
        AiServiceResponseState.Error(statusCode, message ?: "Unknown error")
    }
}