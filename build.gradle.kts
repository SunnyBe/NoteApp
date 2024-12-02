buildscript {
    repositories {
        mavenCentral()
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
    alias(libs.plugins.lint.ktlint.jlleitschuh) apply false
 // Setup [Plugin secrets] - https://github.com/google/secrets-gradle-plugin
}

val quiet: String by project

tasks.register("updateLocks") {
    group = "dependency locking"
    description = "Runs --write-locks for all sub-projects"

    doLast {
        subprojects.forEach { subproject ->
        println("Running for ${subproject.name}")
            exec {
                workingDir = subproject.projectDir
                val command = mutableListOf("gradle", "dependencies", "--write-locks")
                if (quiet == "true") {
                    command.add("--quiet")
                }
                commandLine = command
            }
        }
    }
}