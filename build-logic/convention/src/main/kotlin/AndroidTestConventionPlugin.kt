import com.android.build.gradle.TestExtension
import com.sunday.noteapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure(TestExtension::class.java) {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 31
//                configureGradleManagedDevices(this)
            }
        }
    }

}