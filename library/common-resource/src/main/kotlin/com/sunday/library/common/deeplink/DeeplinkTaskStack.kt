package com.sunday.library.common.deeplink

data class DeeplinkTaskStack(
    val intentTask: List<android.content.Intent>,
    val baseScreen: BaseScreen
)