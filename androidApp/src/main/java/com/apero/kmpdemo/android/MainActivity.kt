package com.apero.kmpdemo.android

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.apero.composeapp.presentation.AppScreen
import com.apero.composeapp.utils.designsystem.style.DesignSystemTheme
import com.apero.kmpdemo.android.ext.displayCutoutAboveAndroidP
import com.apero.kmpdemo.android.ext.setSystemBarsVisibility

class MainActivity : ComponentActivity() {
    private var statusBarColor: Color = Color.Black
    private var isLightStatusBar: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.displayCutoutAboveAndroidP()
        this.setSystemBarsVisibility(shouldShowStatusBar = true, shouldShowNavigationBar = false)
        this.setSystemBarColor(statusBarColor)
        hideSystemNavigationBar(window)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppScreen()
            }
        }
    }

    private fun setSystemBarColor(
        statusBarColor: Color = Color.Transparent,
        navigationBarColor: Color = Color.Transparent,
        isLightStatusBar: Boolean = this.isLightStatusBar
    ) {
        this.statusBarColor = statusBarColor
        this.isLightStatusBar = isLightStatusBar

        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            isLightStatusBar
    }

    fun hideSystemNavigationBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = ViewCompat.getWindowInsetsController(window.decorView)
            if (controller != null) {
                controller.hide(WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
}