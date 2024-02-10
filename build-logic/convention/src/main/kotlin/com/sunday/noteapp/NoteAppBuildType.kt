package com.sunday.noteapp

enum class NoteAppBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(applicationIdSuffix = ".dev"),
    RELEASE(applicationIdSuffix = null),
    BENCHMARK(applicationIdSuffix = ".benchmark")
}