package com.sunday.noteapp

import com.sunday.noteapp.utils.asDependency
import com.sunday.noteapp.utils.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
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
        add("ktlintRuleset", "ktlint.ruleset.compose".asDependency(this@configureKtLint))
        add("ktlintRuleset", "com.pinterest.ktlint:ktlint-ruleset-standard:0.41.0")
    }
}

internal fun Project.configureDetekt(detektExtension: DetektExtension) {
    detektExtension.apply {
        source.setFrom("src")
        parallel = true
        ignoredBuildTypes = listOf("release")
        ignoredFlavors = listOf("prod")
        ignoredVariants = listOf("prodRelease")
        basePath = projectDir.absolutePath
        buildUponDefaultConfig = true
        allRules = true
        config.setFrom(files("$rootDir/detekt.yml"))
    }

    tasks.withType<Detekt> {
        reports {
            sarif.required.set(false)
            html.required.set(false)
            txt.required.set(true)
            md.required.set(false)
        }
    }

    tasks.register<Detekt>("detektAll") {
        description = "Runs Detekt on all modules."
        parallel = true
        setSource(files("src/main/java", "src/main/kotlin"))
        config.setFrom(files("$rootDir/detekt.yml"))
        include("**/*.kt", "**/*.kts")
        exclude("**/build/**")
    }

    dependencies {
        add("detektPlugins", "ru.kode:detekt-rules-compose:1.4.0")
    }
}