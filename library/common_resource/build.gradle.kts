@Suppress("DSL_SCOPE_VIOLATION") // temp fix -> https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.noteapp.android.library)
    alias(libs.plugins.noteapp.android.hilt)
}

android {
    namespace = "com.sunday.library.common.resource"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.LENIENT
    lockAllConfigurations()
}