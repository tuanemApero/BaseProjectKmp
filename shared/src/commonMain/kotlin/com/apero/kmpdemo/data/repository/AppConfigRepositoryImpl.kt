package com.apero.kmpdemo.data.repository

import com.apero.kmpdemo.domain.repository.AppConfigRepository

class AppConfigRepositoryImpl : AppConfigRepository {
    override val languageCode: String =  "en"
}