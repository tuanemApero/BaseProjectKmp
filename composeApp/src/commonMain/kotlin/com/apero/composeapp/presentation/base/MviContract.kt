package com.apero.composeapp.presentation.base

interface UiState

interface UiIntent

interface UiEffect


interface MviContract<S : UiState, I : UiIntent, E : UiEffect> {
    val state: S

    fun processIntent(intent: I)
}