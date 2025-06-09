package com.apero.composeapp.utils.designsystem.style

import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenWidthDp(): Int {
    val bounds = UIScreen.mainScreen.bounds
    return CGRectGetWidth(bounds).toInt()
}