package com.sunday.note.deeplink

import android.net.Uri
import com.sunday.common.deeplink.DeeplinkDispatcher
import com.sunday.common.deeplink.DeeplinkTaskStack
import javax.inject.Inject

class NoteFeatureDeeplinkDispatcher @Inject constructor(
    // dependencies
) : DeeplinkDispatcher {
    override fun process(uri: Uri?): DeeplinkTaskStack? {
        val authority = uri?.authority
        val pathSegment = uri?.pathSegments?.first()

        if (authority != "com.korekt") return null
        return null
    }
}



