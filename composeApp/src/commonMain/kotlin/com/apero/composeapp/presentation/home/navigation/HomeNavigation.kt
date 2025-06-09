package com.apero.composeapp.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.apero.composeapp.presentation.home.HomeScreen
import com.apero.composeapp.presentation.navigation.HomeRoute
import com.apero.composeapp.presentation.navigation.ProfileRoute
import com.apero.composeapp.presentation.profile.ProfileScreen

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
) {
    composable<HomeRoute> {
        HomeScreen()
    }
}