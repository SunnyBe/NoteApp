import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.sunday.noteapp.configureFlavors
import com.sunday.noteapp.configureKotlinAndroid
import com.sunday.noteapp.disableUnnecessaryAndroidTests
import com.sunday.noteapp.utils.libs
import com.sunday.noteapp.utils.pluginId
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryPlugin : org.gradle.api.Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                libs.findPlugin("android-library").pluginId?.let { apply(it) }
                libs.findPlugin("kotlin-android").pluginId?.let { apply(it) }
                libs.findPlugin("noteapp-android-hilt").pluginId?.let { apply(it) }
                libs.findPlugin("noteapp-android-lint").pluginId?.let { apply(it) }
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