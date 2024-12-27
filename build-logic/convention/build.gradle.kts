import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    alias(libs.plugins.lint.ktlint.jlleitschuh) apply true // Enables the ktlint plugin in convention plugins
    alias(libs.plugins.lint.detekt.arturbosch) apply true // Enables the detekt plugin in convention plugins
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

ktlint {
    version.set("1.4.1")
    debug.set(true)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    filter {
        exclude(
            "**/generated/**",
            "build.gradle.kts" // Exclude due to chain-method-continuation rule
        )
        include("**/kotlin/**")
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ktlint.gradle)
    compileOnly(libs.detekt.gradle)
    ktlintRuleset(libs.ktlint.ruleset.compose)
    detektPlugins(libs.detekt.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.noteapp.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidCompose") {
            id = libs.plugins.noteapp.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposePlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.noteapp.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposePlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.noteapp.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.noteapp.android.feature.get().pluginId
            implementationClass = "AndroidFeaturePlugin"
        }

        register("androidTest") {
            id = "noteapp.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("androidFlavors") {
            id = libs.plugins.noteapp.android.application.flavors.get().pluginId
            implementationClass = "AndroidApplicationFlavorsPlugin"
        }

        register("androidHilt") {
            id = libs.plugins.noteapp.android.hilt.get().pluginId
            implementationClass = "AndroidHiltPlugin"
        }

        register("androidApplicationJacoco") {
            id = libs.plugins.noteapp.android.application.jacoco.get().pluginId
            implementationClass = "AndroidApplicationJacocoPlugin"
        }

        register("androidLibraryJacoco") {
            id = libs.plugins.noteapp.android.library.jacoco.get().pluginId
            implementationClass = "AndroidLibraryJacocoPlugin"
        }

        register("androidLibraryLint") {
            id = libs.plugins.noteapp.android.lint.get().pluginId
            implementationClass = "AndroidLibraryLintPlugin"
        }
    }
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.LENIENT
    lockAllConfigurations()
}