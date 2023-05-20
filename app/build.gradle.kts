import com.sunday.noteapp.NoteAppBuildType

plugins {
    id("noteapp.android.application")
    id("noteapp.android.application.compose")
    id("noteapp.android.application.flavors")
    id("noteapp.android.dagger.hilt")
    id("noteapp.android.application.jacoco")
}

android {
    namespace = "com.sunday.noteapp"

    defaultConfig {
        applicationId = "com.sunday.noteapp"
        versionCode = 1
        versionName = "0.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = NoteAppBuildType.DEBUG.applicationIdSuffix
        }
        getByName("release") {
            isMinifyEnabled = false
            applicationIdSuffix = NoteAppBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Extra projects
    implementation(project(":core:cache"))
    implementation(project(":feature:onboarding"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.toolingPreview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.timber)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.junit.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.compose.ui.test.junit)
    debugImplementation(libs.androidx.compose.ui.uiTooling)
    debugImplementation(libs.androidx.comopose.ui.test.manifest)
}

val smokeTest: Configuration by configurations.creating {
    extendsFrom(configurations.testImplementation.get())
}