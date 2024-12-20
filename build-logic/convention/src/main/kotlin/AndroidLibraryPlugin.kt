import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.sunday.noteapp.configureFlavors
import com.sunday.noteapp.configureKotlinAndroid
import com.sunday.noteapp.disableUnnecessaryAndroidTests
import com.sunday.noteapp.utils.VersionCatalogMapper
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryPlugin : org.gradle.api.Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(VersionCatalogMapper.PLUGIN_ANDROID_LIBRARY.asPlugin(target))
                apply(VersionCatalogMapper.PLUGIN_ANDROID_KOTLIN.asPlugin(target))
                apply(VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY_HILT.asPlugin(target))
                apply(VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY_LINT.asPlugin(target))
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                configureFlavors(this)

                // e.g core_common_<resource name>
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(project)
            }

            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }

}