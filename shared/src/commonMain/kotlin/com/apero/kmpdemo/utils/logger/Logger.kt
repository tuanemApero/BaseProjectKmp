package com.apero.kmpdemo.utils.logger

/**
 * A simple cross-platform logging utility
 */
expect object Logger {
    /**
     * Log a debug message
     */
    fun d(tag: String, message: String)

    /**
     * Log an error message
     */
    fun e(tag: String, message: String)
    
    /**
     * Log an info message
     */
    fun i(tag: String, message: String)
}