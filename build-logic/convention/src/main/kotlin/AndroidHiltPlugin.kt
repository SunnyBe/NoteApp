import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_DAGGER_HILT
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_KSP
import com.sunday.noteapp.utils.asPlugin
import com.sunday.noteapp.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(PLUGIN_KSP.asPlugin(target))
                apply(PLUGIN_DAGGER_HILT.asPlugin(target))
            }

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
                "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}