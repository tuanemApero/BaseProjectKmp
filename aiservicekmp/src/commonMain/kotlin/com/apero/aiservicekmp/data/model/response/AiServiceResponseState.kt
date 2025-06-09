package com.apero.aiservicekmp.data.model.response

sealed class AiServiceResponseState<out T> {
    data class Success<T>(val data: T) : AiServiceResponseState<T>()
    data class Error(val code: Int, val message: String?) : AiServiceResponseState<Nothing>()
    data object Default : AiServiceResponseState<Nothing>()
}
