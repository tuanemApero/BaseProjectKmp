package com.apero.kmpdemo.utils.logger

/**
 * iOS-specific implementation of Logger
 */
actual object Logger {
    actual fun d(tag: String, message: String) {
        println("D/$tag: $message")
    }

    actual fun e(tag: String, message: String) {
        println("E/$tag: $message")
    }

    actual fun i(tag: String, message: String) {
        println("I/$tag: $message")
    }
}