package com.apero.kmpdemo.di

import com.apero.kmpdemo.data.common.createDataStore
import com.apero.kmpdemo.data.common.remoteconfig.RemoteConfigRepository
import com.apero.kmpdemo.data.common.remoteconfig.RemoteConfigRepositoryImpl
import com.apero.kmpdemo.data.common.sharepref.DataStoreRepo
import com.apero.kmpdemo.data.common.sharepref.DataStoreRepoImpl
import com.apero.kmpdemo.data.repository.AppConfigRepositoryImpl
import com.apero.kmpdemo.data.repository.StyleItemRepositoryImpl
import com.apero.kmpdemo.domain.repository.AppConfigRepository
import com.apero.kmpdemo.domain.repository.StyleItemRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<StyleItemRepository> {
        StyleItemRepositoryImpl(get())
    }

    single { createDataStore() }
    single<RemoteConfigRepository> { RemoteConfigRepositoryImpl() }
    single<DataStoreRepo> { DataStoreRepoImpl(get()) }

    single<AppConfigRepository> { AppConfigRepositoryImpl() }
}