package com.apero.aiservicekmp.di

import org.koin.core.module.Module


fun getAiServiceModule() : List<Module> {
    return listOf(
        repositoryModule,
        clientModule,
        repositoryModule
    )
}