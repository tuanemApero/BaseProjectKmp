package com.apero.kmpdemo.data.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.java.KoinJavaComponent.getKoin

actual fun createDataStore(dataStoreName: String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(produceFile = {
        getKoin().get<Context>().filesDir.resolve(
            dataStoreName
        ).absolutePath.toPath()
    })
}