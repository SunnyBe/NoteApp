package com.sunday.library.service.deeplink

import android.net.Uri

interface DeeplinkDispatcher {
    fun process(uri: Uri?): DeeplinkTaskStack?
}
