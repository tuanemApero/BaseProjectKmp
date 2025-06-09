package com.apero.composeapp

import androidx.compose.ui.window.ComposeUIViewController
import com.apero.composeapp.presentation.AppScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    lateinit var vc: UIViewController
    return ComposeUIViewController (
        configure = {
            doInitKoinIos()
        }
    ) {
        AppScreen()
    }
}
