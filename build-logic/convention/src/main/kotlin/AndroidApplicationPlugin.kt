import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureGradleManagedDevices
import com.sunday.noteapp.configureKotlinAndroid
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_APPLICATION
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_ANDROID_KOTLIN
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY_HILT
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_NOTEAPP_LIBRARY_LINT
import com.sunday.noteapp.utils.asPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply(PLUGIN_ANDROID_APPLICATION.asPlugin(target))
            apply(PLUGIN_ANDROID_KOTLIN.asPlugin(target))
            apply(PLUGIN_NOTEAPP_LIBRARY_HILT.asPlugin(target)) // Uses KSP
            apply(PLUGIN_NOTEAPP_LIBRARY_LINT.asPlugin(target))
        }

        val applicationExtension = target.extensions.getByType(ApplicationExtension::class.java)
        target.configureKotlinAndroid(applicationExtension)
        applicationExtension.defaultConfig.targetSdk = 34
        target.configureGradleManagedDevices(applicationExtension)
        // TODO Consider using ApplicationComponentExtension for printing test apks, and badging as follow up
    }
}