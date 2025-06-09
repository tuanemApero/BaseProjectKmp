package com.apero.composeapp.utils.designsystem.component

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.apero.composeapp.utils.designsystem.color.AppColors
import com.apero.composeapp.utils.designsystem.ext.clickableSingle
import com.apero.composeapp.utils.designsystem.ext.pxToDp
import com.apero.composeapp.utils.designsystem.style.LocalCustomTypography
import kmpdemo.composeapp.generated.resources.Res
import kmpdemo.composeapp.generated.resources.ic_see_all
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomAppBar(
    title: String? = null,
    onBackClick: (() -> Unit)? = null,
    style: AppBarStyle = AppBarStyle.TitleOnly,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    endIconTint: Color? = null,
    fontTitleStyle : TextStyle = LocalCustomTypography.current.Body.large,
    colorBackground : Color = AppColors.Material.background,
    modifier: Modifier = Modifier,
    onStartIconClick: (() -> Unit)? = null,
    onEndIconClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.pxToDp()),
        color = colorBackground
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Start section (Back button or custom start icon)
            if (style != AppBarStyle.TitleOnly) {
                when {
                    startIcon != null && onStartIconClick != null -> {
                        Box (
                            modifier = Modifier
                                .padding(start = 18.pxToDp())
                                .align(Alignment.CenterStart)
                                .clip(RoundedCornerShape(999.pxToDp()))
                                .clickableSingle {
                                    onStartIconClick()
                                }.padding(8.pxToDp())
                        ) {
                            Icon(
                                painter = startIcon,
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                    onBackClick != null -> {
                        Box (
                            modifier = Modifier
                                .padding(start = 16.pxToDp())
                                .align(Alignment.CenterStart)
                                .clip(RoundedCornerShape(999.pxToDp()))
                                .clickableSingle {
                                    onBackClick()
                                }.padding(8.pxToDp())
                        ) {
                            Icon(
                                tint = Color.White,
                                painter = painterResource(Res.drawable.ic_see_all),
                                contentDescription = "Back",
                            )
                        }
                    }
                }
            }


            if(title != null){
                Text(
                    text = title,
                    style = fontTitleStyle,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }


            if (style == AppBarStyle.BackTitleButton && endIcon != null && onEndIconClick != null) {
                Box(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .padding(start = 16.pxToDp())
                        .align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(999.pxToDp()))
                        .clickableSingle {
                            onEndIconClick()
                        }.padding(8.pxToDp())
                ) {

                    Image(
                        painter = endIcon,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomAppBarPreview() {
    CustomAppBar(
        title = "Demo",
        onBackClick = {},
        style = AppBarStyle.BackTitle,
        onStartIconClick = {},
    )
}

enum class AppBarStyle {
    BackTitle,           // Style 1: Back icon and title
    BackTitleButton,     // Style 2: Back icon, title and end button/icon
    TitleOnly            // Style 3: Only title
}