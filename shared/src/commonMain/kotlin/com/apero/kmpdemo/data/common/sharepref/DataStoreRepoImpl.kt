package com.apero.kmpdemo.data.common.sharepref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepoImpl(private val dataStore: DataStore<Preferences>) : DataStoreRepo {
    private object Key {
        val TIME_OUT_PRELOAD_KEY = booleanPreferencesKey("TIME_OUT_PRELOAD_KEY")
    }

    override suspend fun isTimeOutPreload(): Boolean {
        return dataStore.data
            .map { it[Key.TIME_OUT_PRELOAD_KEY] == true }
            .first()
    }
}
