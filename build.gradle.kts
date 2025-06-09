buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.build.konfig)
        classpath(libs.google.services)
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.android.kotlin.multiplatform.library).apply(false)
    alias(libs.plugins.jetbrains.kotlin.compose).apply(false)
    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.room).apply(false)
    alias(libs.plugins.google.service) apply false
//    alias(libs.plugins.firebase.crashlytics) apply false
}
