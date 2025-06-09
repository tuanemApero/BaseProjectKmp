package com.apero.composeapp.presentation

import androidx.lifecycle.viewModelScope
import com.apero.composeapp.presentation.base.BaseViewModel
import com.apero.kmpdemo.SharedModuleConfig
import com.apero.kmpdemo.data.common.remoteconfig.RemoteConfigRepository
import kotlinx.coroutines.launch

class AppViewModel(
    private val remoteConfigRepository: RemoteConfigRepository
) : BaseViewModel<AppContract.State, AppContract.Intent, AppContract.Effect>(
    AppContract.State()
) {
    override fun processIntent(intent: AppContract.Intent) {

    }

    init {
        SharedModuleConfig.initialize()
        viewModelScope.launch {
            remoteConfigRepository.fetchRemoteConfig()
                .onSuccess {

                }
                .onFailure {

                }
        }

    }
}