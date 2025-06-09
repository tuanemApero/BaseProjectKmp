package com.apero.composeapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.apero.composeapp.presentation.home.navigation.navigateToHome
import com.apero.composeapp.presentation.navigation.NavigationItem
import com.apero.composeapp.presentation.profile.navigation.navigateToProfile
import com.apero.kmpdemo.domain.repository.AppConfigRepository
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    appConfigRepository: AppConfigRepository
): AppState {
    return remember(navController) {
        AppState(navController, coroutineScope, appConfigRepository)
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    appConfigRepository: AppConfigRepository
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    fun navigateToTopLevelDestination(topLevelDestination: NavigationItem.BottomBarScreen) {
        trace("Navigation: $topLevelDestination") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                NavigationItem.BottomBarScreen.Home -> {
                    navController.navigateToHome(
                        topLevelNavOptions
                    )
                }

                NavigationItem.BottomBarScreen.Profile -> {
                    navController.navigateToProfile(
                        topLevelNavOptions
                    )
                }

            }
        }
    }
}