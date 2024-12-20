import com.android.build.gradle.LibraryExtension
import com.sunday.noteapp.configureAndroidCompose
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_LIBRARY
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(PLUGIN_ANDROID_LIBRARY.asPlugin(target))
            // Specify extra plugins in projects build.gradle file

            val libraryExtension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(libraryExtension)
        }
    }
}