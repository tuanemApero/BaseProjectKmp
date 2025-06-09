package com.apero.composeapp.utils.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.apero.composeapp.utils.designsystem.ext.pxToDp
import com.apero.composeapp.utils.designsystem.color.AppColors
import com.apero.composeapp.utils.designsystem.style.LocalCustomTypography
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    title: String? = null,
    message: String? = null,
    style: DialogStyle = DialogStyle.Default,
    primaryButton: DialogButton? = null,
    secondaryButton: DialogButton? = null,
    customContent: (@Composable () -> Unit)? = null,
    exitIconPainterTopEnd: DrawableResource? = null,
    exitIconPainterTopStart: DrawableResource? = null,
    iconDialogMain: DrawableResource? = null,
    customButtons: (@Composable ColumnScope.() -> Unit)? = null
) {
    BackHandler(onBack = onDismiss)

    val transitionAlpha by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = tween(durationMillis = 200)
    )

    val offsetY by animateDpAsState(
        targetValue = if (show) 0.pxToDp() else 300.pxToDp(),
        animationSpec = tween(durationMillis = 200)
    )

    val colors = AppColors.Material
    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.Material.background.copy(alpha = 0.5f))
                .clickable(
                    onClick = onDismiss,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(y = offsetY)
                    .alpha(transitionAlpha)
                    .fillMaxWidth(0.83f)
                    .background(
                        AppColors.Material.onBackground,
                        shape = RoundedCornerShape(32.pxToDp())
                    )
                    .clickable(
                        onClick = {

                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            ) {
                if (exitIconPainterTopEnd != null) {
                    Image(
                        painter = painterResource(exitIconPainterTopEnd),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 25.pxToDp(), end = 20.pxToDp())
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = onDismiss
                            )
                    )
                }

                if (exitIconPainterTopStart != null) {
                    Image(
                        painter = painterResource(exitIconPainterTopStart),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 25.pxToDp(), start = 20.pxToDp())
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = onDismiss
                            ),
                        colorFilter = ColorFilter.tint(color = AppColors.Material.surface)
                    )
                }
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.pxToDp(),
                        vertical = 24.pxToDp()
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (iconDialogMain != null) {
                        Image(
                            painter = painterResource(iconDialogMain),
                            contentDescription = null,
                            modifier = Modifier.size(100.pxToDp())
                        )
                        Spacer(modifier = Modifier.height(24.pxToDp()))
                    }

                    if (!title.isNullOrEmpty()) {
                        Text(
                            text = title,
                            style = LocalCustomTypography.current.Title.large,
                            textAlign = TextAlign.Center,
                            color = colors.surface,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }

                    if (!message.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(4.pxToDp()))

                        Text(
                            text = message,
                            style = LocalCustomTypography.current.Body.small,
                            textAlign = TextAlign.Center,
                            color = colors.surface,
                        )
                    }

                    customContent?.let {
                        Spacer(modifier = Modifier.height(10.pxToDp()))
                        it()
                        Spacer(modifier = Modifier.height(10.pxToDp()))
                    }

                    Spacer(modifier = Modifier.height(24.pxToDp()))

                    if (customButtons != null) {
                        customButtons()
                    } else when (style) {
                        DialogStyle.Default -> DefaultButtonLayout(
                            primaryButton,
                            secondaryButton
                        )

                        DialogStyle.Horizontal -> HorizontalButtonLayout(
                            primaryButton,
                            secondaryButton
                        )

                        DialogStyle.Vertical -> VerticalButtonLayout(
                            primaryButton,
                            secondaryButton
                        )

                        DialogStyle.SingleButton -> SingleButtonLayout(primaryButton)
                        DialogStyle.Settings -> SettingButtonLayout(
                            primaryButton,
                            secondaryButton
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CustomDialog2(
    show: Boolean,
    onDismiss: () -> Unit,
    title: String? = null,
    message: String? = null,
    style: DialogStyle = DialogStyle.Default,
    primaryButton: DialogButton? = null,
    secondaryButton: DialogButton? = null,
    customContent: (@Composable () -> Unit)? = null,
    exitIconPainterTopEnd: DrawableResource? = null,
    exitIconPainterTopStart: DrawableResource? = null,
    iconDialogMain: DrawableResource? = null,
    customButtons: (@Composable ColumnScope.() -> Unit)? = null
) {
    val colors = AppColors.Material
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(30.pxToDp())),
                color = colors.onBackground
            ) {
                Box {
                    if (exitIconPainterTopEnd != null) {
                        Image(
                            painter = painterResource(exitIconPainterTopEnd),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 25.pxToDp(), end = 20.pxToDp())
                                .clickable(
                                    indication = null,
                                    interactionSource = null,
                                    onClick = onDismiss
                                )
                        )
                    }

                    if (exitIconPainterTopStart != null) {
                        Image(
                            painter = painterResource(exitIconPainterTopStart),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 25.pxToDp(), start = 20.pxToDp())
                                .clickable(
                                    indication = null,
                                    interactionSource = null,
                                    onClick = onDismiss
                                ),
                            colorFilter = ColorFilter.tint(color = AppColors.Material.surface)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 20.pxToDp(),
                            vertical = 24.pxToDp()
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (iconDialogMain != null) {
                            Image(
                                painter = painterResource(iconDialogMain),
                                contentDescription = null,
                                modifier = Modifier.size(100.pxToDp())
                            )
                            Spacer(modifier = Modifier.height(24.pxToDp()))
                        }

                        if (!title.isNullOrEmpty()) {
                            Text(
                                text = title,
                                style = LocalCustomTypography.current.Title.medium,
                                textAlign = TextAlign.Center,
                                color = colors.surface,
                                modifier = Modifier.fillMaxWidth(0.8f)
                            )
                        }

                        if (!message.isNullOrEmpty()) {
                            Text(
                                text = message,
                                style = LocalCustomTypography.current.Body.small,
                                textAlign = TextAlign.Center,
                                color = colors.surface,
                            )
                        }

                        customContent?.let {
                            Spacer(modifier = Modifier.height(10.pxToDp()))
                            it()
                            Spacer(modifier = Modifier.height(10.pxToDp()))
                        }

                        Spacer(modifier = Modifier.height(24.pxToDp()))

                        if (customButtons != null) {
                            customButtons()
                        } else when (style) {
                            DialogStyle.Default -> DefaultButtonLayout(
                                primaryButton,
                                secondaryButton
                            )

                            DialogStyle.Horizontal -> HorizontalButtonLayout(
                                primaryButton,
                                secondaryButton
                            )

                            DialogStyle.Vertical -> VerticalButtonLayout(
                                primaryButton,
                                secondaryButton
                            )

                            DialogStyle.SingleButton -> SingleButtonLayout(primaryButton)
                            DialogStyle.Settings -> SettingButtonLayout(
                                primaryButton,
                                secondaryButton
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DefaultButtonLayout(
    primaryButton: DialogButton?,
    secondaryButton: DialogButton?
) {
    val colors = AppColors.Material
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.pxToDp())
    ) {
        secondaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                border = BorderStroke(1.pxToDp(), colors.primary),
                contentPadding = PaddingValues(4.pxToDp()),
            ) {
                Text(
                    text = button.text,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.pxToDp()),
                    style = LocalCustomTypography.current.Body.large,
                    color = colors.primary
                )
            }
        }
        primaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary
                ),
                contentPadding = PaddingValues(4.pxToDp())

            ) {
                Text(
                    maxLines = 1,
                    text = button.text,
                    style = LocalCustomTypography.current.Body.large,
                    color = colors.surface,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.pxToDp()),

                    )
            }
        }
    }
}

@Composable
private fun SettingButtonLayout(
    primaryButton: DialogButton?,
    secondaryButton: DialogButton?
) {
    val colors = AppColors.Material
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.pxToDp())
    ) {
        secondaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(2f).height(52.pxToDp()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                border = BorderStroke(1.pxToDp(), colors.primary),
                contentPadding = PaddingValues(1.pxToDp()),
            ) {
                Text(
                    text = button.text,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 4.pxToDp()),
                    style = LocalCustomTypography.current.Body.large,
                    color = colors.primary
                )
            }
        }
        primaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(3f).height(52.pxToDp()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary
                ),
                contentPadding = PaddingValues(1.pxToDp())

            ) {
                Text(
                    maxLines = 1,
                    text = button.text,
                    style = LocalCustomTypography.current.Body.large,
                    color = colors.surface,
                    modifier = Modifier.padding(horizontal = 4.pxToDp()),

                    )
            }
        }
    }
}

@Composable
private fun HorizontalButtonLayout(
    primaryButton: DialogButton?,
    secondaryButton: DialogButton?
) {
    val colors = AppColors.Material
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.pxToDp())
    ) {
        // Secondary Button
        secondaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                contentPadding = PaddingValues(0.pxToDp()),
                border = BorderStroke(1.pxToDp(), colors.outline)
            ) {
                Text(
                    text = button.text,
                    style = LocalCustomTypography.current.Title.medium,
                    color = colors.surface,
                    modifier = Modifier.padding(vertical = 14.pxToDp())
                )
            }
        }

        // Primary Button with icon support
        primaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                contentPadding = PaddingValues(0.pxToDp()),
                border = BorderStroke(1.pxToDp(), colors.outline)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    button.icon?.let { icon ->
                        icon()
                        Spacer(modifier = Modifier.width(8.pxToDp()))
                    }
                    VslTextView(
                        text = button.text,
                        textStyle = LocalCustomTypography.current.Title.medium
                            .copy(color = colors.surface),
                        maxLines = 1,
                        marqueeEnabled = true,
                        modifier = Modifier.padding(vertical = 14.pxToDp())
                    )
                }
            }
        }
    }
}

@Composable
private fun VerticalButtonLayout(
    primaryButton: DialogButton?,
    secondaryButton: DialogButton?
) {
    val colors = AppColors.Material
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.pxToDp()),
        verticalArrangement = Arrangement.spacedBy(16.pxToDp())
    ) {
        primaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.fillMaxWidth().height(52.pxToDp()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                border = BorderStroke(1.pxToDp(), colors.outline)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    button.icon?.let { icon ->
                        icon()
                        Spacer(modifier = Modifier.width(8.pxToDp()))
                    }
                    Text(
                        text = button.text,
                        style = LocalCustomTypography.current.Title.medium,
                        color = colors.surface
                    )
                }
            }
        }
        secondaryButton?.let { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier.fillMaxWidth().height(52.pxToDp()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.surface
                ),
                border = BorderStroke(1.pxToDp(), colors.outline)
            ) {
                Text(
                    text = button.text,
                    style = LocalCustomTypography.current.Title.medium,
                    color = colors.surface
                )
            }
        }
    }
}


@Composable
private fun SingleButtonLayout(primaryButton: DialogButton?) {
    val colors = AppColors.Material
    primaryButton?.let { button ->
        Button(
            onClick = button.onClick,
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.surface
            ),
            border = BorderStroke(1.pxToDp(), colors.outline)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                button.icon?.let { icon ->
                    icon()
                    Spacer(modifier = Modifier.width(8.pxToDp()))
                }
                Text(
                    text = button.text,
                    style = LocalCustomTypography.current.Title.medium,
                    color = colors.surface,
                    modifier = Modifier.padding(vertical = 10.pxToDp())
                )
            }
        }
    }
}


data class DialogButton(
    val text: String,
    val onClick: () -> Unit,
    val icon: @Composable (() -> Unit)? = null
)

enum class DialogStyle {
    Default,
    Horizontal,
    Vertical,
    SingleButton,
    Settings,
}

@Preview()
@Composable
fun CustomDialogPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Default Dialog",
            message = "This is a sample message for the default dialog style with two buttons.",
            style = DialogStyle.Default,
            primaryButton = DialogButton("Confirm", onClick = {}) {},
            secondaryButton = DialogButton("Cancel", onClick = {}) {}
        )
    }
}

@Preview()
@Composable
fun CustomDialogSettingsPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Default Dialog",
            message = "This is a sample message for the default dialog style with two buttons.",
            style = DialogStyle.Settings,
            primaryButton = DialogButton("Confirm", onClick = {}) {},
            secondaryButton = DialogButton("Cancel", onClick = {}) {}
        )
    }
}

@Preview()
@Composable
fun CustomDialogVerticalPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Vertical Style Dialog",
            message = "This is a sample message for the vertical dialog style.",
            style = DialogStyle.Vertical,
            primaryButton = DialogButton("Confirm", onClick = {}) {},
            secondaryButton = DialogButton("Cancel", onClick = {}) {}
        )
    }
}

@Preview()
@Composable
fun CustomDialogSingleButtonPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Single Button Dialog",
            message = "This is a sample message for the single button dialog style.",
            style = DialogStyle.SingleButton,
            primaryButton = DialogButton("OK", onClick = {}) {}
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun CustomDialogWithCustomContentPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Custom Content Dialog",
            message = "This dialog includes custom content.",
            style = DialogStyle.Default,
            primaryButton = DialogButton("Confirm", onClick = {}) {},
            secondaryButton = DialogButton("Cancel", onClick = {}) {},
            customContent = {
                // Add your custom content here
                Text(
                    text = "This is custom content",
                    style = LocalCustomTypography.current.Body.medium,
                    color = LocalCustomColors.current.textPrimary
                )
            },
            exitIconPainter = painterResource(R.drawable.ic_send)
        )
    }
}*/

@Preview()
@Composable
fun CustomDialogNoMessagePreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Dialog Without Message",
            style = DialogStyle.Settings,
            primaryButton = DialogButton("OK", onClick = {}) {},
            secondaryButton = DialogButton("Cancel", onClick = {}) {}
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun CustomDialogHorizontalWithIconPreview() {
    MaterialTheme {
        CustomDialog(
            show = true,
            onDismiss = {},
            title = "Horizontal Style Dialog",
            message = "This is a sample message for the horizontal dialog style with an icon.",
            style = DialogStyle.Horizontal,
            primaryButton = DialogButton(
                text = "Confirm",
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = null
                    )
                }
            ),
            secondaryButton = DialogButton(
                text = "Cancel",
                onClick = {}
            )
        )
    }
}*/
