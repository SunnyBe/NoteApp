package com.sunday.noteapp.di

import com.sunday.noteapp.deeplink.AggregateDeeplinkDispatcher
import com.sunday.noteapp.deeplink.AppDeeplinkDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindDeeplinkDispatcher(
        aggregateDeeplinkDispatcher: AggregateDeeplinkDispatcher
    ): AppDeeplinkDispatcher
}