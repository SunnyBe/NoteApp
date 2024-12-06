package com.sunday.noteapp

import com.sunday.noteapp.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jlleitschuh.gradle.ktlint.KtlintExtension

internal fun Project.configureKtLint(ktlintExtension: KtlintExtension) {
    ktlintExtension.apply {
        version.set("1.4.1")
        debug.set(true)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    dependencies {
        add("ktlintRuleset", libs.findLibrary("ktlint.ruleset.compose").get())
        add("ktlintRuleset", "com.pinterest.ktlint:ktlint-ruleset-standard:0.41.0")
    }
}