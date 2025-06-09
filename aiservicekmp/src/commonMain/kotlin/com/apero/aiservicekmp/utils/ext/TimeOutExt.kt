package com.apero.aiservicekmp.utils.ext

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> withCatching(coroutineContext: CoroutineContext = Dispatchers.IO, action: suspend CoroutineScope.() -> T) = withContext(coroutineContext) {
    runCatching {
        action()
    }.onFailure {
        if (it is CancellationException) {
            throw it
        }
    }
}