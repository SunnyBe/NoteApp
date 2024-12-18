import com.sunday.noteapp.NoteAppBuildType

plugins {
    alias(libs.plugins.noteapp.android.application)
    alias(libs.plugins.noteapp.android.application.compose)
    alias(libs.plugins.noteapp.android.application.flavors)
    alias(libs.plugins.noteapp.android.application.jacoco)
}

android {
    namespace = "com.sunday.noteapp"

    defaultConfig {
        applicationId = "com.sunday.noteapp"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations.addAll(listOf("en", "fr", "de"))
    }

    buildTypes {
        debug {
            applicationIdSuffix = NoteAppBuildType.DEBUG.applicationIdSuffix
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            applicationIdSuffix = NoteAppBuildType.RELEASE.applicationIdSuffix
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            testProguardFiles("test-proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.library.commonResource)
    implementation(projects.library.designSystem)
    implementation(projects.feature.note)
    androidTestImplementation(projects.library.testing)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.timber)

    testImplementation(libs.androidx.test.core)
    testImplementation(libs.test.junit)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}

val smokeTest: Configuration by configurations.creating {
    extendsFrom(configurations.testImplementation.get())
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.STRICT
    lockAllConfigurations()
}