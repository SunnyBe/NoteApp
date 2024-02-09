@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.noteapp.android.feature)
}

android {
    namespace = "com.sunday.note"

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
    androidTestImplementation(libs.androidx.test.ext.junit) // TODO Remove after build-logic fix
}