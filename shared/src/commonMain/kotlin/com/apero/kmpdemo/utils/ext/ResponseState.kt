package com.apero.kmpdemo.utils.ext

sealed class ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val code: Int, val message: String?) : ResponseState<Nothing>()
    data object Default : ResponseState<Nothing>()
}