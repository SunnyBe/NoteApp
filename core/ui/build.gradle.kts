@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.noteapp.android.library)
    alias(libs.plugins.noteapp.android.library.compose)
    alias(libs.plugins.noteapp.android.library.jacoco)
}

android {
    namespace = "com.sunday.core.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.hilt.android.testing)

    androidTestImplementation(projects.core.testing)
}