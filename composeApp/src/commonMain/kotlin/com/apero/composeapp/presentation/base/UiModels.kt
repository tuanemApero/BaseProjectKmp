package com.apero.composeapp.presentation.base

sealed class ResourceState<out T> : UiState {
    data class Loading<T>(val progress: Float? = null) : ResourceState<T>()

    data class Success<T>(val data: T) : ResourceState<T>()

    data class Error<T>(
        val message: String? = null,
        val exception: Throwable? = null
    ) : ResourceState<T>()

    val isLoading: Boolean get() = this is Loading

    val isSuccess: Boolean get() = this is Success

    val isError: Boolean get() = this is Error

    val dataOrNull: T? get() = (this as? Success)?.data

    fun <R> map(transform: (T) -> R): ResourceState<R> {
        return when (this) {
            is Loading -> Loading(progress)
            is Success -> Success(transform(data))
            is Error -> Error(message, exception)
        }
    }
}