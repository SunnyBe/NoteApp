package com.sunday.noteapp.deeplink

import android.net.Uri
import com.sunday.common.deeplink.DeeplinkTaskStack

interface AppDeeplinkDispatcher {
    fun dispatchUri(uri: Uri): DeeplinkTaskStack?
}