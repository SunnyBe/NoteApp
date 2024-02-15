package com.sunday.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NoteAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.w("$LOG_TAG: This device is low on memory!")
    }

    companion object {
        const val LOG_TAG = "NoteApplication"
    }
}