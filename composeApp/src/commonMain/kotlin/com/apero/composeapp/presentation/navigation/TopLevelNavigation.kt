package com.apero.composeapp.presentation.navigation

import com.apero.composeapp.presentation.navigation.NavigationItem.BottomBarScreen.Home
import com.apero.composeapp.presentation.navigation.NavigationItem.BottomBarScreen.Profile
import kmpdemo.composeapp.generated.resources.Res
import kmpdemo.composeapp.generated.resources.bottom_bar_title_ai_video
import kmpdemo.composeapp.generated.resources.bottom_bar_title_profile
import kmpdemo.composeapp.generated.resources.ic_bottom_bar_ai_video
import kmpdemo.composeapp.generated.resources.ic_bottom_bar_profile
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KClass

sealed class NavigationItem(
    val route: KClass<*>,
    val icon: DrawableResource,
    val title: StringResource
) {
    sealed class BottomBarScreen(
        val bRoute: KClass<*>,
        val bIcon: DrawableResource,
        val bTitle: StringResource,
    ) : NavigationItem(bRoute, bIcon, bTitle) {
        data object Home : BottomBarScreen(
            bRoute = HomeRoute::class,
            bIcon = Res.drawable.ic_bottom_bar_ai_video,
            bTitle = Res.string.bottom_bar_title_ai_video,
        )

        data object Profile : BottomBarScreen(
            bRoute = ProfileRoute::class,
            bIcon = Res.drawable.ic_bottom_bar_profile,
            bTitle = Res.string.bottom_bar_title_profile,
        )
    }

    sealed class NoBottomBarScreen(
        val nRoute: KClass<*>,
        val nIcon: DrawableResource,
        val nTitle: StringResource,
    ) : NavigationItem(nRoute, nIcon, nTitle) {

        data object Categories : NoBottomBarScreen(
            nRoute = CategoriesRoute::class,
            nIcon = Res.drawable.ic_bottom_bar_ai_video,
            nTitle = Res.string.bottom_bar_title_profile,
        )
    }

}

val listScreenBottomBar = listOf(
    Home, Profile
)

@Serializable
data object HomeRoute

@Serializable
data object ProfileRoute

@Serializable
data object CategoriesRoute
