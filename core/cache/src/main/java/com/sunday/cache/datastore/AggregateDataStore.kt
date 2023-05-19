package com.sunday.cache.datastore

import com.sunday.cache.datastore.app.DeviceDetailDataStore
import com.sunday.cache.datastore.user.UserDetailDataStore

interface AggregateDataStore: DeviceDetailDataStore, UserDetailDataStore