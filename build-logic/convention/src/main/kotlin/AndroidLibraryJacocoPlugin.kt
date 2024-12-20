import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.sunday.noteapp.configureJacoco
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_LIBRARY
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_JACOCO
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryJacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(PLUGIN_JACOCO.asPlugin(target))
                apply(PLUGIN_ANDROID_LIBRARY.asPlugin(target))
            }
            val componentsExtension = extensions.getByType<LibraryAndroidComponentsExtension>()
            configureJacoco(componentsExtension)
        }
    }
}