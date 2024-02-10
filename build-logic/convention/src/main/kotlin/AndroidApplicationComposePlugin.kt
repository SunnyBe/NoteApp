import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(applicationExtension)
        }
    }

}