package com.apero.composeapp.utils.ext

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.apero.composeapp.presentation.navigation.HomeRoute
import com.apero.composeapp.presentation.navigation.ProfileRoute
import kotlin.reflect.KClass

fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

fun NavDestination?.shoudlShowBottomBar(): Boolean {
    if (this == null) {
        return true
    }
    return hasRoute(HomeRoute::class) || hasRoute(ProfileRoute::class)
}
