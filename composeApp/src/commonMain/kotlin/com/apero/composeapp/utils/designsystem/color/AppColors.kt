package com.apero.composeapp.utils.designsystem.color

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {

    /**/
    val white = Color(0xFFFFFFFF)
    val red = Color(0xFFE9355A)
    val grey500 = Color(0xFF9E9E9E)
    /**/

    object Material {
        val primary = Color(0xFFD0BCFF)
        val onPrimary = Color(0xFF381E72)
        val backgroundSecondary = Color(0XFFF2F2F7)
        val primaryContainer = Color(0xFF4F378B)
        val onPrimaryContainer = Color(0xFFEADDFF)

        val secondary = Color(0xFFCCC2DC)
        val onSecondary = Color(0xFF332D41)
        val secondaryContainer = Color(0xFF4A4458)
        val onSecondaryContainer = Color(0xFFE8DEF8)

        val tertiary = Color(0xFFEFB8C8)
        val onTertiary = Color(0xFF492532)
        val tertiaryContainer = Color(0xFF633B48)
        val onTertiaryContainer = Color(0xFFFFD8E4)

        val error = Color(0xFFF2B8B5)
        val onError = Color(0xFF601410)
        val errorContainer = Color(0xFF8C1D18)
        val onErrorContainer = Color(0xFFF9DEDC)

        val background = Color(0xFF141218)
        val onBackground = Color(0xFFE6E1E5)
        val surface = Color(0xFF141218)
        val onSurface = Color(0xFFE6E0E9)
        val surfaceVariant = Color(0xFF49454F)
        val onSurfaceVariant = Color(0xFFCAC4D0)

        val outline = Color(0xFF938F99)
        val outlineVariant = Color(0xFF49454F)
        val surfaceTint = Color(0xFFD0BCFF)
        val scrim = Color(0xFF000000)

        val inverseSurface = Color(0xFFE6E1E5)
        val inverseOnSurface = Color(0xFF313033)
        val inversePrimary = Color(0xFF6750A4)

        val primaryFixed = Color(0xFFEADDFF)
        val onPrimaryFixed = Color(0xFF21005D)
        val primaryFixedDim = Color(0xFFD0BCFF)
        val onPrimaryFixedVariant = Color(0xFF4F378B)

        val secondaryFixed = Color(0xFFE8DEF8)
        val onSecondaryFixed = Color(0xFF1D192B)
        val secondaryFixedDim = Color(0xFFCCC2DC)
        val onSecondaryFixedVariant = Color(0xFF4A4458)

        val tertiaryFixed = Color(0xFFFFD8E4)
        val onTertiaryFixed = Color(0xFF31111D)
        val tertiaryFixedDim = Color(0xFFEFB8C8)
        val onTertiaryFixedVariant = Color(0xFF633B48)

        val surfaceDim = Color(0xFF141218)
        val surfaceBright = Color(0xFF413738)
        val surfaceContainerLowest = Color(0xFF0F0D13)
        val surfaceContainerLow = Color(0xFF1D1A22)
        val surfaceContainer = Color(0xFF211F26)
        val surfaceContainerHigh = Color(0xFF2B2930)
        val surfaceContainerHighest = Color(0xFF33303A)
    }
}

data class CustomColorScheme(
    val material3: ColorScheme,
    val primaryFixed: Color,
    val onPrimaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixedVariant: Color,

    val secondaryFixed: Color,
    val onSecondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixedVariant: Color,

    val tertiaryFixed: Color,
    val onTertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixedVariant: Color
)