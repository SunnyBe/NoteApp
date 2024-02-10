package com.sunday.core.cache.datastore

import com.sunday.core.cache.datastore.app.DeviceDetailDataStore
import com.sunday.core.cache.datastore.user.UserDetailDataStore

interface AggregateDataStore: DeviceDetailDataStore, UserDetailDataStore