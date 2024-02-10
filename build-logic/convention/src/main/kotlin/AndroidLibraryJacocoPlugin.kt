import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.sunday.noteapp.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryJacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.gradle.jacoco")
                apply("com.android.library")
            }
            val componentsExtension = extensions.getByType<LibraryAndroidComponentsExtension>()
            configureJacoco(componentsExtension)
        }
    }
}