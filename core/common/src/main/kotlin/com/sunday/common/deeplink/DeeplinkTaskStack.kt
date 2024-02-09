package com.sunday.common.deeplink

data class DeeplinkTaskStack(
    val intentTask: List<android.content.Intent>,
    val baseScreen: BaseScreen
)