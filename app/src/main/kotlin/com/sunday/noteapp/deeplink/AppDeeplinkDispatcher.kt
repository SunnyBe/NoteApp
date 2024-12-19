package com.sunday.noteapp.deeplink

import android.net.Uri
import com.sunday.library.service.deeplink.DeeplinkTaskStack

interface AppDeeplinkDispatcher {
    fun dispatchUri(uri: Uri): DeeplinkTaskStack?
}
