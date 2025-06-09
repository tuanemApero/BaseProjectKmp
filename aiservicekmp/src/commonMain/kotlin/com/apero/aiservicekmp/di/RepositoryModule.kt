package com.apero.aiservicekmp.di

import com.apero.aiservicekmp.GenerateHttpClient
import com.apero.aiservicekmp.StyleHttpClient
import com.apero.aiservicekmp.data.datasource.GenerateVideoRemoteDatasource
import com.apero.aiservicekmp.data.datasource.StyleRemoteDatasource
import com.apero.aiservicekmp.data.datasource.impl.GenerateVideoRemoteDataSourceImpl
import com.apero.aiservicekmp.data.repository.AiVideoRepository
import com.apero.aiservicekmp.data.repository.impl.AiVideoRepositoryImpl
import com.apero.aiservicekmp.data.datasource.impl.StyleRemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<GenerateVideoRemoteDatasource> { GenerateVideoRemoteDataSourceImpl(get(GenerateHttpClient)) }
    single<StyleRemoteDatasource> { StyleRemoteDataSourceImpl(get(StyleHttpClient)) }

    single<AiVideoRepository> { AiVideoRepositoryImpl(get()) }
}