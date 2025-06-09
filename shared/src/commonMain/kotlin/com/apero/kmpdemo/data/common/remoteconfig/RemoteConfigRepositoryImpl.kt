package com.apero.kmpdemo.data.common.remoteconfig

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.apero.kmpdemo.data.common.createDataStore
import com.apero.kmpdemo.utils.json.standardJson
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.app
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@Serializable
data class SaleConfig(
    @SerialName("sale_countdown_time") val saleCountdownTime: Long = 300,
    @SerialName("sale_cta_text") val saleCTAText: String = "Continue",
    @SerialName("sale_video_url") val saleVideoUrl: String = "https://static.apero.vn/cms-upload/superx_nami/ob/Tiger.mp4",
    @SerialName("sale_features_list") val saleFeaturesList: List<String> = listOf(
        "Unlock All Styles", "99 AI Videos", "Fastest Update Trend"
    )
)

class RemoteConfigRepositoryImpl : RemoteConfigRepository {
    private val FILE_NAME = "remote_config_offline.preferences_pb"
    private val firebaseRemoteConfig = Firebase.remoteConfig(Firebase.app)
    private val dataStoreRemoteConfig = createDataStore(FILE_NAME)


    object Key {
        internal val TEST = OfflineKey.StringKey("enable_feedback_scr", "default")
        internal val Sale = OfflineKey.JsonKey("sale", SaleConfig(), SaleConfig.serializer())
    }

    sealed interface OfflineKey<T> {
        data class StringKey(
            val key: String,
            val default: String,
            val dataStoreKey: Preferences.Key<String> = stringPreferencesKey(key)
        ) : OfflineKey<String>

        data class IntKey(
            val key: String,
            val default: Int,
            val dataStoreKey: Preferences.Key<Int> = intPreferencesKey(key)
        ) : OfflineKey<Int>

        data class LongKey(
            val key: String,
            val default: Long,
            val dataStoreKey: Preferences.Key<Long> = longPreferencesKey(key)
        ) : OfflineKey<Long>

        data class BooleanKey(
            val key: String,
            val default: Boolean,
            val dataStoreKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
        ) : OfflineKey<Boolean>

        data class JsonKey<T>(
            val key: String,
            val default: T,
            val serializer: KSerializer<T>,
            val dataStoreKey: Preferences.Key<String> = stringPreferencesKey(key)
        ) : OfflineKey<T>
    }

    private suspend fun OfflineKey<*>.saveLocal() {
        runCatching {
            when (this) {
                is OfflineKey.StringKey -> dataStoreRemoteConfig.edit { preferences ->
                    preferences[dataStoreKey] = firebaseRemoteConfig.getValue(key).asString()
                }

                is OfflineKey.LongKey -> dataStoreRemoteConfig.edit { preferences ->
                    preferences[dataStoreKey] = firebaseRemoteConfig.getValue(key).asLong()
                }

                is OfflineKey.BooleanKey -> dataStoreRemoteConfig.edit { preferences ->
                    preferences[dataStoreKey] = firebaseRemoteConfig.getValue(key).asBoolean()
                }

                is OfflineKey.JsonKey<*> -> dataStoreRemoteConfig.edit { preferences ->
                    preferences[dataStoreKey] = firebaseRemoteConfig.getValue(key).asString()
                }

                is OfflineKey.IntKey -> dataStoreRemoteConfig.edit { preferences ->
                    preferences[dataStoreKey] = firebaseRemoteConfig.getValue(key).asDouble().toInt()
                }
            }
        }.onFailure { it.printStackTrace() }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> OfflineKey<T>.getLocal(): Flow<T> {
        return when (this) {
            is OfflineKey.StringKey -> runCatching {
                dataStoreRemoteConfig.data.map { it[dataStoreKey] ?: default }
            }.getOrElse { default } as Flow<T>

            is OfflineKey.LongKey -> runCatching {
                dataStoreRemoteConfig.data.map { it[dataStoreKey] ?: default }
            }.getOrElse { default } as Flow<T>

            is OfflineKey.IntKey -> runCatching {
                dataStoreRemoteConfig.data.map { it[dataStoreKey] ?: default }
            }.getOrElse { default } as Flow<T>

            is OfflineKey.BooleanKey -> runCatching {
                dataStoreRemoteConfig.data.map { it[dataStoreKey] ?: default }
            }.getOrElse { default } as Flow<T>

            is OfflineKey.JsonKey<T> -> runCatching {
                dataStoreRemoteConfig.data.map { preferences ->
                    val json = preferences[dataStoreKey]
                    if (json != null) {
                        try {
                            standardJson.decodeFromString(serializer, json)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            default
                        }
                    } else {
                        default
                    }
                }
            }.getOrElse { default } as Flow<T>
        }
    }

    private suspend fun syncOffline() {
        Key.TEST.saveLocal()
        Key.Sale.saveLocal()
    }

    override suspend fun fetchRemoteConfig(): Result<Boolean> {
        return runCatching {
            firebaseRemoteConfig.settings {
                fetchTimeout = 1.minutes
                minimumFetchInterval = 1.hours
            }
            val result = firebaseRemoteConfig.fetchAndActivate()
            syncOffline()
            result
        }.onFailure {
            it.printStackTrace()
        }
    }


    override suspend fun testValue(): Flow<SaleConfig> {
        return Key.Sale.getLocal()
    }
}
