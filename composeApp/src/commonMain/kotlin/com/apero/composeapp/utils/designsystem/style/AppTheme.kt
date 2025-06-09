package com.apero.composeapp.utils.designsystem.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.apero.artimind.kmp.theme.style.CustomTypography
import com.apero.composeapp.utils.designsystem.color.darkColorScheme
import com.apero.composeapp.utils.designsystem.color.lightColorScheme
import kmpdemo.composeapp.generated.resources.Res
import kmpdemo.composeapp.generated.resources.Urbanist_Bold
import kmpdemo.composeapp.generated.resources.Urbanist_Medium
import kmpdemo.composeapp.generated.resources.Urbanist_Regular
import kmpdemo.composeapp.generated.resources.Urbanist_SemiBold
import org.jetbrains.compose.resources.Font

expect fun getScreenWidthDp() : Int

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography> {
    error("No typography provided")
}

val LocalCustomColors = staticCompositionLocalOf { darkColorScheme }

val LocalScreenScale = compositionLocalOf { 1f }

@Composable
fun DesignSystemTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val screenWidth = remember { getScreenWidthDp() }
    val baseWidth = 393f // iPhone 14 Pro Max
    val scale = remember(screenWidth) { screenWidth / baseWidth }

    val regularFont = Font(Res.font.Urbanist_Regular, weight = FontWeight.Normal)
    val mediumFont = Font(Res.font.Urbanist_Medium, weight = FontWeight.Medium)
    val semiBoldFont = Font(Res.font.Urbanist_SemiBold, weight = FontWeight.SemiBold)
    val boldFont = Font(Res.font.Urbanist_Bold, weight = FontWeight.Bold)

    val fontFamily = remember {
        FontFamily(
            regularFont,
            mediumFont,
            semiBoldFont,
            boldFont
        )
    }

    val typography = remember(scale) { CustomTypography.Companion.create(scale, fontFamily) }
    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalCustomColors provides colorScheme,
        LocalCustomTypography provides typography,
        LocalScreenScale provides scale,
    ) {
        MaterialTheme(
            content = content,
            colorScheme = colorScheme.material3
        )
    }
}