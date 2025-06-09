package com.apero.composeapp.presentation

import com.apero.composeapp.presentation.base.UiEffect
import com.apero.composeapp.presentation.base.UiIntent
import com.apero.composeapp.presentation.base.UiState

interface AppContract {

    data class State(
        val demo: Boolean = false
    ) : UiState

    sealed interface Intent : UiIntent {

    }

    sealed interface Effect : UiEffect {

    }
}