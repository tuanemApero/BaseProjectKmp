package com.apero.composeapp.presentation.base

import kotlinx.coroutines.flow.StateFlow

interface StateHandler<S : UiState> {
    fun handleState(state: S)
}

interface EffectHandler<E : UiEffect> {
    fun handleEffect(effect: E)
}

suspend fun <S : UiState> StateHandler<S>.collectState(stateFlow: StateFlow<S>) {
    stateFlow.collect { state ->
        handleState(state)
    }
}