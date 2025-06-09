package com.apero.composeapp.di

import org.koin.core.module.Module

fun getComposeAppModule() : List<Module> = buildList {
    add(viewModelModule)
}