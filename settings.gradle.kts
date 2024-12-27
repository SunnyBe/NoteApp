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
include(":library:common_resource")
include(":library:common_config")
include(":library:design_system")
include(":library:testing")
include(":feature:note")
include(":library:service")
