package com.sunday.noteapp.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Provides Version catalog's libs for dependency provision in the specified project
 */
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Optional<Provider<PluginDependency>>.pluginId: String?
    get() = getOrNull()?.get()?.pluginId