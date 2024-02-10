import com.android.build.gradle.LibraryExtension
import com.sunday.noteapp.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            // Specify extra plugins in projects build.gradle file

            val libraryExtension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(libraryExtension)
        }
    }
}