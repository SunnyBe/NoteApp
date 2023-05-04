package com.sunday.noteapp

enum class NoteAppBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".dev"),
    RELEASE,
    BENCHMARK(".benchmark")
}