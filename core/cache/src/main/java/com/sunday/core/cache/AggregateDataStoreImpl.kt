package com.sunday.core.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.sunday.core.cache.datastore.AggregateDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class AggregateDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context,
    @Named("detail") val detailDataStore: DataStore<Preferences>,
    @Named("user") val userDetailDataStore: DataStore<Preferences>
) : AggregateDataStore {

    private val appStartCount = intPreferencesKey("app_start_counter")
    override fun appStartCount(): Flow<Int> =
        detailDataStore.data.map { preferences: Preferences ->
            preferences[appStartCount] ?: 0
        }

    override suspend fun incrementAppStartCount() {
        detailDataStore.edit { preferences ->
            preferences[appStartCount] = (preferences[appStartCount] ?: 0) + 1
        }
    }

}