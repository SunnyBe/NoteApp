buildscript {
    repositories {
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.lint.ktlint.jlleitschuh) apply false
    alias(libs.plugins.lint.detekt.arturbosch) apply false
    // Setup [Plugin secrets] - https://github.com/google/secrets-gradle-plugin
}