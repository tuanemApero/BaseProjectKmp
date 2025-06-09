package com.apero.kmpdemo.android

import android.app.Application
import com.apero.composeapp.di.getComposeAppModule
import com.apero.kmpdemo.di.getSharedModules
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.analytics.analytics
import dev.gitlive.firebase.initialize
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun getAppModules() : List<Module> {
    return getComposeAppModule() + getSharedModules()
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initFirebase()
        initKoin()
    }

    private fun initFirebase() {
        Firebase.initialize(this)
        Firebase.analytics.setAnalyticsCollectionEnabled(true)
//        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                getAppModules()
            )
        }
    }
}