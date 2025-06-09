package com.apero.composeapp

import com.apero.composeapp.di.getComposeAppModule
import com.apero.kmpdemo.di.getSharedModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun getAppModules() : List<Module> {
    return getComposeAppModule() + getSharedModules()
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            getAppModules()
        )
    }

// Called by iOS
fun doInitKoinIos() = initKoin {}