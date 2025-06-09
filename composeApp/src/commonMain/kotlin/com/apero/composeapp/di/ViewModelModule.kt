package com.apero.composeapp.di

import com.apero.composeapp.presentation.AppViewModel
import com.apero.composeapp.presentation.home.HomeViewModel
import com.apero.composeapp.presentation.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::AppViewModel)
}