package com.apero.composeapp.utils.designsystem.color

import androidx.compose.material3.darkColorScheme

val lightColorScheme = CustomColorScheme(
    material3 = darkColorScheme(
        primary = AppColors.Material.primary,
        onPrimary = AppColors.Material.onPrimary,
        primaryContainer = AppColors.Material.primaryContainer,
        onPrimaryContainer = AppColors.Material.onPrimaryContainer,
        inversePrimary = AppColors.Material.inversePrimary,

        secondary = AppColors.Material.secondary,
        onSecondary = AppColors.Material.onSecondary,
        secondaryContainer = AppColors.Material.secondaryContainer,
        onSecondaryContainer = AppColors.Material.onSecondaryContainer,

        tertiary = AppColors.Material.tertiary,
        onTertiary = AppColors.Material.onTertiary,
        tertiaryContainer = AppColors.Material.tertiaryContainer,
        onTertiaryContainer = AppColors.Material.onTertiaryContainer,

        background = AppColors.Material.background,
        onBackground = AppColors.Material.onBackground,
        surface = AppColors.Material.surface,
        onSurface = AppColors.Material.onSurface,

        surfaceVariant = AppColors.Material.surfaceVariant,
        onSurfaceVariant = AppColors.Material.onSurfaceVariant,

        surfaceTint = AppColors.Material.surfaceTint,
        inverseSurface = AppColors.Material.inverseSurface,
        inverseOnSurface = AppColors.Material.inverseOnSurface,

        error = AppColors.Material.error,
        onError = AppColors.Material.onError,
        errorContainer = AppColors.Material.errorContainer,
        onErrorContainer = AppColors.Material.onErrorContainer,

        outline = AppColors.Material.outline,
        outlineVariant = AppColors.Material.outlineVariant,
        scrim = AppColors.Material.scrim,

        surfaceDim = AppColors.Material.surfaceDim,
        surfaceBright = AppColors.Material.surfaceBright,
        surfaceContainerLowest = AppColors.Material.surfaceContainerLowest,
        surfaceContainerLow = AppColors.Material.surfaceContainerLow,
        surfaceContainer = AppColors.Material.surfaceContainer,
        surfaceContainerHigh = AppColors.Material.surfaceContainerHigh,
        surfaceContainerHighest = AppColors.Material.surfaceContainerHighest,
    ),
    primaryFixed = AppColors.Material.primaryFixed,
    onPrimaryFixed = AppColors.Material.onPrimaryFixed,
    primaryFixedDim = AppColors.Material.primaryFixedDim,
    onPrimaryFixedVariant = AppColors.Material.onPrimaryFixedVariant,

    secondaryFixed = AppColors.Material.secondaryFixed,
    onSecondaryFixed = AppColors.Material.onSecondaryFixed,
    secondaryFixedDim = AppColors.Material.secondaryFixedDim,
    onSecondaryFixedVariant = AppColors.Material.onSecondaryFixedVariant,

    tertiaryFixed = AppColors.Material.tertiaryFixed,
    onTertiaryFixed = AppColors.Material.onTertiaryFixed,
    tertiaryFixedDim = AppColors.Material.tertiaryFixedDim,
    onTertiaryFixedVariant = AppColors.Material.onTertiaryFixedVariant
)