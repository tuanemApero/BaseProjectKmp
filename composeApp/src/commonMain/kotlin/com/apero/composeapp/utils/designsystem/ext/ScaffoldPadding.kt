package com.apero.composeapp.utils.designsystem.ext

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.apero.composeapp.utils.designsystem.style.LocalScreenScale

data class ScaffoldPadding(val paddings: PaddingValues)

val LocalScaffoldPadding = compositionLocalOf { ScaffoldPadding(PaddingValues(0.dp)) }

@Composable
fun Number.pxToDp(): Dp {
    val scale = LocalScreenScale.current
    return (this.toFloat() * scale).dp
}

fun Number.pxToDpUnsafe(): Dp {
    // Use in non-compose (if cached scale is exposed)
    val scale = 1f // fallback
    return (this.toFloat() * scale).dp
}
