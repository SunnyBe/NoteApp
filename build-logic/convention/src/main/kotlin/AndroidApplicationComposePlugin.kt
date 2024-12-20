import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureAndroidCompose
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_APPLICATION
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply { PLUGIN_ANDROID_APPLICATION.asPlugin(target) }

            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(applicationExtension)
        }
    }
}