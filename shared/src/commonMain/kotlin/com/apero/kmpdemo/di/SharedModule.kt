package com.apero.kmpdemo.di

import com.apero.aiservicekmp.di.getAiServiceModule
import org.koin.core.module.Module

fun getSharedModules(): List<Module> {
    return listOf(
        databaseModule, repositoryModule, useCaseModule
    ) + getAiServiceModule()
}