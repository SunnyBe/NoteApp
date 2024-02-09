package com.sunday.note.di

import com.sunday.common.deeplink.DeeplinkDispatcher
import com.sunday.note.deeplink.NoteFeatureDeeplinkDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeeplinkModule {
    @Binds
    abstract fun bindNoteDeeplinkDispatcher(
        deeplinkDispatcher: NoteFeatureDeeplinkDispatcher
    ): DeeplinkDispatcher
}