package com.apero.composeapp.presentation.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.apero.composeapp.presentation.base.BaseViewModel
import com.apero.composeapp.presentation.home.HomeContract
import com.apero.composeapp.presentation.navigation.ProfileRoute

class ProfileViewModel(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeContract.State, HomeContract.Intent, HomeContract.Effect>(
    HomeContract.State()
) {
    override fun processIntent(intent: HomeContract.Intent) {

    }
    val route = savedStateHandle.toRoute<ProfileRoute>()

}