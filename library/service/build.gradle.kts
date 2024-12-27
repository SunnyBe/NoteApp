plugins {
    alias(libs.plugins.noteapp.android.library)
    alias(libs.plugins.noteapp.android.library.compose)
    alias(libs.plugins.noteapp.android.library.jacoco)
}

android {
    namespace = "com.sunday.library.service"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.library.commonResource)
    implementation(projects.library.commonConfig)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.test.junit)
    testImplementation(projects.library.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.LENIENT
    lockAllConfigurations()
}
