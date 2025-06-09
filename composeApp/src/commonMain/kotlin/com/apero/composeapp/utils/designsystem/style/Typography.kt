package com.apero.artimind.kmp.theme.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp


data class TitleTypography(
    val large: TextStyle,
    val medium: TextStyle,
    val small: TextStyle,
)

abstract class CustomTypography {
    abstract val Display: TitleTypography
    abstract val Headline: TitleTypography
    abstract val Title: TitleTypography
    abstract val Body: TitleTypography
    abstract val Label: TitleTypography

    companion object {
        fun create(scale: Float, fontFamily: FontFamily): CustomTypography {
            fun textStyle(weight: FontWeight, size: Float, lineHeight: Float, letterSpacing: Float = 0f) = TextStyle(
                fontSize = (size * scale).sp,
                lineHeight = (lineHeight * scale).sp,
                fontWeight = weight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing.sp,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )

            fun displayGroup() = TitleTypography(
                large = textStyle(FontWeight.Bold, 57f, 64f, -0.25f),
                medium = textStyle(FontWeight.Bold, 45f, 52f),
                small = textStyle(FontWeight.Bold, 36f, 44f),
            )
            fun headlineGroup() = TitleTypography(
                large = textStyle(FontWeight.Bold, 32f, 40f),
                medium = textStyle(FontWeight.Bold, 28f, 36f),
                small = textStyle(FontWeight.Bold, 24f, 32f),
            )
            fun titleGroup() = TitleTypography(
                large = textStyle(FontWeight.Bold, 22f, 28f),
                medium = textStyle(FontWeight.Bold, 16f, 24f, 0.15f),
                small = textStyle(FontWeight.Bold, 14f, 20f, 0.1f),
            )
            fun bodyGroup() = TitleTypography(
                large = textStyle(FontWeight.Normal, 16f, 24f, 0.5f),
                medium = textStyle(FontWeight.Normal, 14f, 20f, 0.25f),
                small = textStyle(FontWeight.Normal, 12f, 16f, 0.4f),
            )
            fun labelGroup() = TitleTypography(
                large = textStyle(FontWeight.Medium, 14f, 20f, 0.1f),
                medium = textStyle(FontWeight.Medium, 12f, 16f, 0.5f),
                small = textStyle(FontWeight.Medium, 11f, 16f, 0.5f),
            )

            return object : CustomTypography() {
                override val Display = displayGroup()
                override val Headline = headlineGroup()
                override val Title = titleGroup()
                override val Body = bodyGroup()
                override val Label = labelGroup()
            }
        }
    }
}

//Text(
    //text = "No video categories found",
    //style = LocalCustomTypography.current.Title.medium, // example using
    //color = Color.White,
    //textAlign = TextAlign.Center,
    //modifier = Modifier.padding(16.dp)
//)