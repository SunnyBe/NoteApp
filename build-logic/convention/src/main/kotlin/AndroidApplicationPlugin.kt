import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureGradleManagedDevices
import com.sunday.noteapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("noteapp.android.dagger.hilt") // Includes ksp
            apply("noteapp.android.library.lint") // lint all application modules
        }
        val applicationExtension = target.extensions.getByType(ApplicationExtension::class.java)
        target.configureKotlinAndroid(applicationExtension)
        applicationExtension.defaultConfig.targetSdk = 34
        target.configureGradleManagedDevices(applicationExtension)
        // TODO Consider using ApplicationComponentExtension for printing test apks, and badging as follow up
    }
}