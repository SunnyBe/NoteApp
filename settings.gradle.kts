pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NoteApp"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // make subprojects accessible objects
include(":app")
include(":core:common")
include(":core:ui")
include(":core:testing")
include(":core:data")
include(":core:cache")
include(":feature:note")
