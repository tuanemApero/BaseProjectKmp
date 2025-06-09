package com.apero.kmpdemo.android.ext

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.setSystemBarsVisibility(shouldShowStatusBar: Boolean = true, shouldShowNavigationBar: Boolean = true) {
    val windowInsetsController =
        WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    if (shouldShowStatusBar.not()) {
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
    }
    if (shouldShowNavigationBar.not()) {
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }
}

fun Activity.displayCutoutAboveAndroidP() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }
}