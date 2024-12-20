import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_APPLICATION
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_JACOCO
import com.sunday.noteapp.configureJacoco
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationJacocoPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(PLUGIN_ANDROID_APPLICATION.asPlugin(target))
                apply(PLUGIN_JACOCO.asPlugin(target))
            }
            val extension = extensions.getByType<ApplicationAndroidComponentsExtension>()
            configureJacoco(extension)
        }
    }
}