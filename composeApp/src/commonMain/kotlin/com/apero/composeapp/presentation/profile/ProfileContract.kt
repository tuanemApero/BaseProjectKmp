package com.apero.composeapp.presentation.profile

import com.apero.composeapp.presentation.base.UiEffect
import com.apero.composeapp.presentation.base.UiIntent
import com.apero.composeapp.presentation.base.UiState

interface ProfileContract {

    data class State(
        val demo: Boolean = false
    ) : UiState

    sealed interface Intent : UiIntent {
    }

    sealed interface Effect : UiEffect {

    }
}