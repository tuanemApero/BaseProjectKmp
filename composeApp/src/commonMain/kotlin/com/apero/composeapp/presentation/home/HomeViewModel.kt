package com.apero.composeapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.apero.composeapp.presentation.base.BaseViewModel
import com.apero.kmpdemo.data.common.remoteconfig.RemoteConfigRepository
import com.apero.kmpdemo.domain.usecase.GetCategoryInHomeUseCase
import com.apero.kmpdemo.utils.ext.ResponseState
import com.apero.kmpdemo.utils.logger.Logger
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val getCategoryInHomeUseCase: GetCategoryInHomeUseCase
) : BaseViewModel<HomeContract.State, HomeContract.Intent, HomeContract.Effect>(
    HomeContract.State()
) {
    override fun processIntent(intent: HomeContract.Intent) {

    }

    init {
        viewModelScope.launch {
            val a = remoteConfigRepository.testValue().first()
            Logger.d("Data", "data : $a")
        }

        viewModelScope.launch {
            when (val res = getCategoryInHomeUseCase()) {
                is ResponseState.Success -> {
                    Logger.d("Data", "data : $res")
                }

                is ResponseState.Error -> {}

                else -> {

                }
            }
        }
    }

}