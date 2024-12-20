package com.sunday.noteapp.deeplink

import android.net.Uri
import com.sunday.feature.note.deeplink.NoteFeatureDeeplinkDispatcher
import javax.inject.Inject

class AggregateDeeplinkDispatcher @Inject constructor(
    private val noteFeatureDeeplinkDispatcher: NoteFeatureDeeplinkDispatcher
) : AppDeeplinkDispatcher {
    private val lisOfDispatcher = listOf(noteFeatureDeeplinkDispatcher)
    private val setOfAllowedAuthority = setOf("com.korekt")

    override fun dispatchUri(uri: Uri): com.sunday.library.service.deeplink.DeeplinkTaskStack? {
        if (lisOfDispatcher.isEmpty()) return null
        if (!setOfAllowedAuthority.contains(uri.authority)) return null
        return when (uri.pathSegments[0]) {
            "/notes" -> noteFeatureDeeplinkDispatcher.process(uri)
            else -> null
        }
    }
}
