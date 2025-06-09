package com.apero.composeapp.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.compose.NavHost
import com.apero.composeapp.presentation.component.BottomBarItem
import com.apero.composeapp.presentation.component.BottomBarView
import com.apero.composeapp.presentation.home.navigation.homeScreen
import com.apero.composeapp.presentation.navigation.HomeRoute
import com.apero.composeapp.presentation.navigation.listScreenBottomBar
import com.apero.composeapp.presentation.profile.navigation.profileScreen
import com.apero.composeapp.utils.ext.isRouteInHierarchy
import com.apero.composeapp.utils.ext.shoudlShowBottomBar
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import com.apero.composeapp.utils.designsystem.style.DesignSystemTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppScreen(
    viewModel: AppViewModel = koinViewModel(),
    appState: AppState = rememberAppState(appConfigRepository = getKoin().get())
) {
    val uisState by viewModel.uiState.collectAsState()

    BackHandler {

    }
    DesignSystemTheme {
        Scaffold(
            topBar = {
                /* no-op */
            },
            bottomBar = {
                if (appState.currentDestination.shoudlShowBottomBar()) {
                    BottomBarView(
                        content = {
                            listScreenBottomBar.forEach { destination ->
                                val selected =
                                    appState.currentDestination.isRouteInHierarchy(destination.bRoute)
                                BottomBarItem(
                                    icon = destination.bIcon,
                                    isSelected = selected,
                                    onItemClick = {
                                        appState.navigateToTopLevelDestination(destination)
                                    },
                                    title = destination.bTitle,
                                    modifier = Modifier
                                )
                            }
                        }
                    )
                }
            }
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = HomeRoute::class,
                modifier = Modifier
                    .padding()
                    .fillMaxSize(),
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(300),
                        initialScale = 0.8f
                    ) + fadeIn(tween(300))
                },
                exitTransition = {
                    scaleOut(
                        animationSpec = tween(300),
                        targetScale = 1.1f
                    ) + fadeOut(tween(300))
                },
                popEnterTransition = {
                    scaleIn(
                        animationSpec = tween(300),
                        initialScale = 1.1f
                    ) + fadeIn(tween(300))
                },
                popExitTransition = {
                    scaleOut(
                        animationSpec = tween(300),
                        targetScale = 0.8f
                    ) + fadeOut(tween(300))
                }
            ) {
                homeScreen()

                profileScreen()
            }
        }
    }
}