package com.apero.kmpdemo.di

import com.apero.kmpdemo.data.local.dao.StyleGenerateDao
import com.apero.kmpdemo.data.local.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val databaseModule = module {
    includes(platformModule())

    single<StyleGenerateDao> { get<AppDatabase>().styleItemDao() }
}