package com.sunday.core.common.deeplink

data class DeeplinkTaskStack(
    val intentTask: List<android.content.Intent>,
    val baseScreen: BaseScreen
)