package com.sunday.noteapp

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

@Suppress("EnumEntryName")
enum class NoteAppFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    demo(FlavorDimension.contentType, ".demo"),
    prod(FlavorDimension.contentType)
}

internal fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: NoteAppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        FlavorDimension.values().forEach { flavorDimension ->
            flavorDimensions += flavorDimension.name
        }
        productFlavors {
            NoteAppFlavor.values().forEach { flavor ->
                create(flavor.name) {
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(flavor)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor && flavor.applicationIdSuffix != null) {
                        this.applicationIdSuffix = flavor.applicationIdSuffix
                    }
                }
            }
        }
    }
}