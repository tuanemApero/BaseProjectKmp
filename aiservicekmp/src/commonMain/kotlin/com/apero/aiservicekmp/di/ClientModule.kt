package com.apero.aiservicekmp.di

import com.apero.aiservicekmp.AiServiceConfig
import com.apero.aiservicekmp.GenerateHttpClient
import com.apero.aiservicekmp.StyleHttpClient
import com.apero.aiservicekmp.common.ClientConfig
import com.apero.aiservicekmp.data.model.provideHttpClient
import com.apero.aiservicekmp.utils.ext.addSignatureHeadersPlugin
import org.koin.dsl.module


val clientModule = module {
    single(StyleHttpClient) {
        provideHttpClient(
            apiConfig = ClientConfig.STYLE_CONFIG
        )
    }

    factory(GenerateHttpClient) {
        provideHttpClient(
            apiConfig = ClientConfig.GENERATE_VIDEO_CONFIG,
            extraConfigs = {
                addSignatureHeadersPlugin(
                    AiServiceConfig.configModel.bundleId,
                    ClientConfig.PUBLIC_KEY,
                    AiServiceConfig.configModel.bundleId,
                    AiServiceConfig.configModel.appName
                )
            }
        )
    }
}