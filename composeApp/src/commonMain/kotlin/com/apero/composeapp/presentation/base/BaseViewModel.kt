package com.apero.composeapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, I : UiIntent, E : UiEffect>(initialState: S) :
    ViewModel(), MviContract<S, I, E> {

    private val _uiState = MutableStateFlow(initialState)

    val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<E>()

    val effect: SharedFlow<E> = _effect.asSharedFlow()

    override val state: S
        get() = _uiState.value

    protected fun setState(reducer: S.() -> S) {
        val newState = state.reducer()
        _uiState.value = newState
    }

    protected fun emitEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}