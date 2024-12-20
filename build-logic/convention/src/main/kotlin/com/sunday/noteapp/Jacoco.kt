package com.sunday.noteapp

import com.android.build.api.variant.AndroidComponentsExtension
import com.sunday.noteapp.utils.asVersion
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

private val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/ui/theme/**"
)

internal fun Project.configureJacoco(androidComponentsExtension: AndroidComponentsExtension<*, *, *>) {
    configure<JacocoPluginExtension> {
        toolVersion = "jacoco".asVersion(this@configureJacoco)
        reportsDirectory.set(layout.buildDirectory.dir("jacoco/jacocoReports"))
        println("Jacoco tool version: $toolVersion Reports dir: ${reportsDirectory.get().asFile.toPath()}")
    }

    val jacocoTestReport = tasks.create("jacocoTestReport")
    androidComponentsExtension.onVariants { variant ->
        val testTaskName = "test${variant.name.capitalized()}UnitTest"

        val reportTask =
            tasks.register("jacoco${testTaskName.capitalized()}Report", JacocoReport::class) {
                dependsOn(testTaskName)

                reports {
                    xml.required.set(true)
                    html.required.set(true)
                    html.outputLocation.set(layout.buildDirectory.dir("jacocoReports/jacocoHtml"))
                }

                // Set class directories
                val classDir = "$buildDir/tmp/kotlin-classes/${variant.name}"
                classDirectories.setFrom(
                    fileTree(classDir) {
                        exclude(coverageExclusions)
                    }
                )
                println("$testTaskName - class directory count: ${classDirectories.files.size}")

                // Set Source directory(ies)
                val javaSrcDir = "$projectDir/src/main/java"
                val kotlinSrcDir = "$projectDir/src/main/kotlin"
                sourceDirectories.setFrom(files(javaSrcDir, kotlinSrcDir))

                // Set execution data
                executionData.setFrom(file("$buildDir/jacoco/$testTaskName.exec"))
            }
        jacocoTestReport.dependsOn(reportTask)
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            // TODO: Consider removing if not we don't add Robolectric
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}