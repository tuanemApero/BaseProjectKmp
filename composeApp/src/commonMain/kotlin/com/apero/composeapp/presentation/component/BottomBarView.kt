package com.apero.composeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.apero.composeapp.utils.designsystem.ext.pxToDp
import com.apero.composeapp.utils.designsystem.style.LocalCustomColors
import com.apero.composeapp.utils.designsystem.style.LocalCustomTypography
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomBarView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = LocalCustomColors.current.material3.surface.copy(alpha = 0.8f),
                RoundedCornerShape(topStart = 24.pxToDp(), topEnd = 24.pxToDp())
            )
            .padding(vertical = 13.pxToDp())
            .padding(bottom = 20.pxToDp()),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Composable
fun BottomBarItem(
    icon: DrawableResource,
    title: StringResource,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorSelected =
        if (isSelected) LocalCustomColors.current.material3.onSurface else LocalCustomColors.current.material3.surfaceVariant

    Column(
        modifier = modifier
            .width(100.pxToDp())
            .clip(RoundedCornerShape(12.pxToDp()))
            .clickable {
                onItemClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "icon $title",
            modifier = Modifier.size(32.pxToDp()),
            tint = colorSelected
        )

        Spacer(modifier = Modifier.height(7.pxToDp()))

        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.wrapContentSize(),
            text = stringResource(title),
            style = LocalCustomTypography.current.Label.medium,
            color = colorSelected
        )
    }
}