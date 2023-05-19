package com.sunday.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.sunday.cache.datastore.AggregateDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AggregateDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context,
    val detailDataStore: DataStore<Preferences>,
    val userDetailDataStore: DataStore<Preferences>
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