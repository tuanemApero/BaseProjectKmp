package com.apero.aiservicekmp.common

enum class Platform {
    ANDROID, IOS;
    companion object
}
expect val Platform.Companion.current: Platform