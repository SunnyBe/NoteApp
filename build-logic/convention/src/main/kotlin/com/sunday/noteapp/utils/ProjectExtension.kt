package com.sunday.noteapp.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import kotlin.jvm.optionals.getOrNull

/**
 * Provides Version catalog's libs for dependency provision in the specified project
 */
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Retrieves the plugin ID for a given alias from the version catalog.
 * Throws an exception if the plugin is not found.
 */
internal fun String.asPlugin(project: Project): String =
    project.libs
        .findPlugin(this)
        .getOrNull()
        ?.orNull
        ?.pluginId
        ?: throw NoSuchElementException("This plugin ($this) was not declared in version catalog")

/**
 * Retrieves the version string for a given alias from the version catalog.
 * Throws an exception if the version is not found or is blank.
 */
internal fun String.asVersion(project: Project): String {
    val version = project.libs
        .findVersion(this)
        .get()
        .toString() // returns empty string for missing version.
    if (version.isBlank()) throw NoSuchElementException("This version ($this) was not declared in version catalog")
    return version
}

/**
 * Retrieves the dependency string for a given alias from the version catalog.
 * Throws an exception if the dependency is not found.
 */
internal fun String.asDependency(project: Project): String {
    val library = project.libs
        .findLibrary(this)
        .getOrNull()
        ?.get() ?: throw NoSuchElementException(
        "This library ($this) was not declared in version catalog"
    )
    return library.toString()
}
