package com.sunday.core.common.deeplink

import android.net.Uri

interface DeeplinkDispatcher {
    fun process(uri: Uri?): DeeplinkTaskStack?
}