@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.noteapp.android.feature)
    alias(libs.plugins.noteapp.android.library.compose)
    alias(libs.plugins.noteapp.android.library.jacoco)
}

android {
    namespace = "com.sunday.feature.note"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.library.commonConfig)
    testImplementation(projects.library.testing)
    androidTestImplementation(projects.library.testing)

    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.hilt.android.testing)
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.STRICT
    lockAllConfigurations()
}