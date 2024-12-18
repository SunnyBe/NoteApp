@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.noteapp.android.library)
}

android {
    namespace = "com.sunday.library.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.library.testing)
    testImplementation(libs.turbine)
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.STRICT
    lockAllConfigurations()
}