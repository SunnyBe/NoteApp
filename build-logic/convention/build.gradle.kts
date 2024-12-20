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
        exclude("**/generated/**")
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
            id = "noteapp.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidCompose") {
            id = "noteapp.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }

        register("androidLibraryCompose") {
            id = "noteapp.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }

        register("androidLibrary") {
            id = "noteapp.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidFeature") {
            id = "noteapp.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }

        register("androidTest") {
            id = "noteapp.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("androidFlavors") {
            id = "noteapp.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsPlugin"
        }

        register("androidHilt") {
            id = "noteapp.android.dagger.hilt"
            implementationClass = "AndroidHiltPlugin"
        }

        register("androidApplicationJacoco") {
            id = "noteapp.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoPlugin"
        }

        register("androidLibraryJacoco") {
            id = "noteapp.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoPlugin"
        }

        register("androidLibraryLint") {
            id = "noteapp.android.library.lint"
            implementationClass = "AndroidLibraryLintPlugin"
        }
    }
}

dependencyLocking {
    lockFile = file("${rootProject.projectDir}/gradle/lockfile/${projectDir.name}.lockfile")
    lockMode = LockMode.LENIENT
    lockAllConfigurations()
}