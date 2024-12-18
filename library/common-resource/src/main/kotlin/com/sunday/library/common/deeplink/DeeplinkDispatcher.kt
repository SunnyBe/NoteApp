package com.sunday.library.common.deeplink

import android.net.Uri

interface DeeplinkDispatcher {
    fun process(uri: Uri?): DeeplinkTaskStack?
}