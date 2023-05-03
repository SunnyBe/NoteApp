package com.sunday.noteapp

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

enum class FlavorDimension {
    contentType
}

enum class NoteAppFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.contentType, ".dev"),
    prod(FlavorDimension.contentType)
}

internal fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: NoteAppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            NoteAppFlavor.values().forEach { mFlavor ->
                create(mFlavor.name) {
                    dimension = mFlavor.dimension.name
                    flavorConfigurationBlock(mFlavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (mFlavor.applicationIdSuffix != null) {
                            this.applicationIdSuffix = mFlavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}