package com.apero.kmpdemo.di

import com.apero.kmpdemo.domain.usecase.GetCategoryInHomeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCategoryInHomeUseCase(get()) }
}