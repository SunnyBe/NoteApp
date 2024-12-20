package com.sunday.noteapp.utils

/**
 * A mapper for version catalog constants.
 * The values should match the exact alias or plugin name as declared in the `libs.versions.toml` file.
 */
internal object VersionCatalogMapper {
    // 3rd party Plugins
    const val PLUGIN_ANDROID_APPLICATION = "android-application"
    const val PLUGIN_ANDROID_KOTLIN = "kotlin-android"
    const val PLUGIN_ANDROID_LIBRARY = "android-library"
    const val PLUGIN_JACOCO = "jacoco"
    const val PLUGIN_KTLINT = "lint-ktlint-jlleitschuh"
    const val PLUGIN_DETEKT = "lint-detekt-arturbosch"
    const val PLUGIN_KSP = "ksp"
    const val PLUGIN_DAGGER_HILT = "hilt"
    // NoteApp Plugins
    const val PLUGIN_NOTEAPP_LIBRARY_LINT = "noteapp-android-lint"
    const val PLUGIN_NOTEAPP_LIBRARY = "noteapp-android-library"
    const val PLUGIN_NOTEAPP_LIBRARY_HILT = "noteapp-android-hilt"
}