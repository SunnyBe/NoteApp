buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.plugins.lint.ktlint)
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.jvm) apply false // Modules apply kotlin.android if necessary
    alias(libs.plugins.lint.ktlint) apply false
    // Setup [Plugin secrets] - https://github.com/google/secrets-gradle-plugin
}