package com.sunday.library.service.deeplink

data class DeeplinkTaskStack(
    val intentTask: List<android.content.Intent>,
    val baseScreen: BaseScreen
)
