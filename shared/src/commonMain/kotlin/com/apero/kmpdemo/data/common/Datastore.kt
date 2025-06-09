package com.apero.kmpdemo.data.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(dataStoreName: String = "appPrefs.preferences_pb"): DataStore<Preferences>