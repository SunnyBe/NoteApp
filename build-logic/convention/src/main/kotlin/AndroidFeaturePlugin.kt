import com.android.build.gradle.LibraryExtension
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY_HILT
import com.sunday.noteapp.utils.asPlugin
import com.sunday.noteapp.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

internal class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(PLUGIN_NOTEAPP_LIBRARY.asPlugin(target))
                apply(PLUGIN_NOTEAPP_LIBRARY_HILT.asPlugin(target))
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("implementation", project(":library:design_system"))
                add("implementation", project(":library:common-config"))
                add("implementation", project(":library:common-resource"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":library:testing"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":library:testing"))
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }
        }
    }
}
