package com.sunday.common.deeplink

import android.net.Uri

interface DeeplinkDispatcher {
    fun process(uri: Uri?): DeeplinkTaskStack?
}