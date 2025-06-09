package com.apero.kmpdemo.data.common.remoteconfig

import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    suspend fun fetchRemoteConfig(): Result<Boolean>
    suspend fun testValue(): Flow<SaleConfig>
}
