package com.sunday.cache.datastore.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sunday.cache.AggregateDataStoreImpl
import com.sunday.cache.datastore.AggregateDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton


private const val DETAIL_PREF = "detail_prefs"
private const val USER_PREF = "user_prefs"

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesAggregateDataStore(
        @ApplicationContext context: Context,
        @Named("detail") detailPrefs: DataStore<Preferences>,
        @Named("user") userPrefs: DataStore<Preferences>
    ): AggregateDataStore {
        return AggregateDataStoreImpl(
            context = context,
            detailDataStore = detailPrefs,
            userDetailDataStore = userPrefs
        )
    }

    @Provides
    @Named("detail")
    @Singleton
    fun providesDevicePreferenceStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, DETAIL_PREF)),
            scope = CoroutineScope(Dispatchers.IO),
            produceFile = { context.preferencesDataStoreFile(DETAIL_PREF) }
        )
    }

    @Provides
    @Named("user")
    @Singleton
    fun providesUserPreferenceStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREF)),
            scope = CoroutineScope(Dispatchers.IO),
            produceFile = { context.preferencesDataStoreFile(USER_PREF) }
        )
    }

}