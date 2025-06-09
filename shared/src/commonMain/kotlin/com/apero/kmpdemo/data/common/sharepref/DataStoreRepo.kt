package com.apero.kmpdemo.data.common.sharepref

interface DataStoreRepo {
    suspend fun isTimeOutPreload(): Boolean
}
