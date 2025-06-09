package com.apero.composeapp.utils.designsystem.style

import android.content.res.Resources

actual fun getScreenWidthDp(): Int {
    return Resources.getSystem().configuration.screenWidthDp
}