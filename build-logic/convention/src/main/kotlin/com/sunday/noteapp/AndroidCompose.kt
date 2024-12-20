package com.sunday.noteapp

import com.android.build.api.dsl.CommonExtension
import com.sunday.noteapp.utils.asDependency
import com.sunday.noteapp.utils.asVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "androidxComposeCompiler".asVersion(this@configureAndroidCompose)
        }

        dependencies {
            val bom = "androidx.compose.bom".asDependency(this@configureAndroidCompose)
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("implementation", "androidx.compose.runtime".asDependency(this@configureAndroidCompose))
        }
    }

    // check- https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableProvider = enableMetricsProvider.orNull == "false"
    if (enableProvider) {
        // metrics files should be stored in build dir
        val metricsDir = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${metricsDir.absolutePath}")
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "false")
    if (enableReports) {
        // metrics files should be stored in build dir
        val reportsDir = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${reportsDir.absolutePath}")
    }

    return metricParameters.toList()
}