package com.sunday.noteapp.deeplink

import android.net.Uri
import com.sunday.core.common.deeplink.DeeplinkTaskStack

interface AppDeeplinkDispatcher {
    fun dispatchUri(uri: Uri): DeeplinkTaskStack?
}