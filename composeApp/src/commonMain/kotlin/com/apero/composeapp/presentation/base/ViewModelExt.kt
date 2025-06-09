package com.apero.composeapp.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <E : UiEffect> Flow<E>.handleEffects(
    scope: CoroutineScope,
    handler: (E) -> Unit
) = onEach(handler).launchIn(scope)

fun <S : UiState> Flow<S>.handleStates(
    scope: CoroutineScope,
    handler: (S) -> Unit
) = onEach(handler).launchIn(scope)

fun <I : UiIntent> BaseViewModel<*, I, *>.intentProcessor(): (I) -> Unit = { processIntent(it) }