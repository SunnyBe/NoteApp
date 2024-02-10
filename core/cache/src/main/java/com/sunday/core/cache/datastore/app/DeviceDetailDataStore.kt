package com.sunday.core.cache.datastore.app

import kotlinx.coroutines.flow.Flow


interface DeviceDetailDataStore {
    fun appStartCount(): Flow<Int>
    suspend fun incrementAppStartCount()
}