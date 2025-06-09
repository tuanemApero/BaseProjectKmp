package com.apero.kmpdemo.di

import com.apero.kmpdemo.data.local.database.AppDatabase
import com.apero.kmpdemo.data.local.database.getAppDatabase
import com.apero.kmpdemo.data.local.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder()
        getAppDatabase(builder)
    }
} 